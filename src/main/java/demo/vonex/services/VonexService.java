package demo.vonex.services;

import demo.vonex.models.AskResponse;
import demo.vonex.models.RequestToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * @author Jitendra Singh.
 */
@Service
public class VonexService {

    private static final String API_DOMAIN = "https://test.vonex.com.au/";

    @Autowired
    RestTemplate restTemplate;

    @Value("${application.api.retry.limit:10}")
    private byte retryCount;

    public RequestToken initiateRequest() {
        return restTemplate.getForObject(buildURI("api/initial-request"), RequestToken.class);
    }

    public AskResponse ask(String token) {
        byte index = 1;
        AskResponse response = restTemplate.getForObject(buildURI("api/ask", "request", token), AskResponse.class);
        while (!response.isSuccess() && index < retryCount) {
            index++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            response = restTemplate.getForObject(buildURI("api/ask", "request", token), AskResponse.class);
        }
        return response;
    }

    public AskResponse answer(String token) {
        return restTemplate.getForObject(buildURI("api/answer", "request", token), AskResponse.class);
    }

    private URI buildURI(String endpoint) {
        return buildURI(endpoint, null, null);
    }

    private URI buildURI(String endpoint, String param, String value) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(buildUrl(endpoint));
        if (!ObjectUtils.isEmpty(param) && !ObjectUtils.isEmpty(value)) {
            builder = builder.queryParam(param, value);
        }
        return builder.build().toUri();
    }

    private String buildUrl(String endpoint) {
        return API_DOMAIN.concat(endpoint);
    }
}

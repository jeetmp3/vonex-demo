package demo.vonex;

import demo.vonex.handler.ApiResponseErrorHandler;
import demo.vonex.handler.UserAgentHeaderInterceptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application {
	public static void main(String [] args) {
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
		System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		template.setErrorHandler(new ApiResponseErrorHandler());
		List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
		interceptorList.add(new UserAgentHeaderInterceptor());
		template.setInterceptors(interceptorList);
		return template;
	}
}

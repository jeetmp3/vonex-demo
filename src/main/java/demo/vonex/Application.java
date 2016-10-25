package demo.vonex;

import demo.vonex.handler.ApiResponseErrorHandler;
import demo.vonex.handler.UserAgentHeaderInterceptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class Application {
	public static void main(String [] args) {
		SpringApplication.run(Application.class, args);		
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		template.setErrorHandler(new ApiResponseErrorHandler());
		template.setInterceptors(Collections.singletonList(new UserAgentHeaderInterceptor()));
		return template;
	}
}

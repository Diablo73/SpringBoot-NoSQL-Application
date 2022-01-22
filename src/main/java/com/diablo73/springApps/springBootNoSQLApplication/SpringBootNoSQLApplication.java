package com.diablo73.springApps.springBootNoSQLApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootNoSQLApplication {

	private static final String ENDPOINT = "https://springboot-nosql-application.diablo73.repl.co/";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNoSQLApplication.class, args);
		System.out.println("\nEndpoint for SpringBootNoSQLApplication :\n" + ENDPOINT + "\n");
	}


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

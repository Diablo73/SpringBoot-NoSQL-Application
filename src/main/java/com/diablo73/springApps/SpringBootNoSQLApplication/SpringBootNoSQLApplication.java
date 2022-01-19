package com.diablo73.springApps.SpringBootNoSQLApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootNoSQLApplication {

	private static final String ENDPOINT = "https://springboot-nosql-application.diablo73.repl.co/";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNoSQLApplication.class, args);
		System.out.println("\nEndpoint for SpringBootNoSQLApplication :\n" + ENDPOINT + "\n");
	}

}

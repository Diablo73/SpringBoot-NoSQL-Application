package com.diablo73.springApps.springBootNoSQLApplication;

import com.diablo73.springApps.springBootNoSQLApplication.monitors.KamonMonitor;
import io.cronitor.client.CronitorClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@SpringBootApplication
public class SpringBootNoSQLApplication {

	private static final String ENDPOINT = "https://springboot-nosql-application.diablo73.repl.co/";

	private static final CronitorClient cronitorClient = new CronitorClient(System.getenv("CRONITOR_API_KEY"));


	public static void main(String[] args) throws IOException {
		try {
			KamonMonitor.kamonInit();
			cronitorClient.run(System.getenv("CRONITOR_MONITOR_KEY"),
					"Initiating SpringBootNoSQLApplication...", Map.ofEntries(Map.entry("count", 1)));
			SpringApplication.run(SpringBootNoSQLApplication.class, args);
			System.out.println("\nEndpoint for SpringBootNoSQLApplication :\n" + ENDPOINT + "\n");
			cronitorClient.complete(System.getenv("CRONITOR_MONITOR_KEY"),
					"SpringBootNoSQLApplication Started Successfully at Time : " + new Date(), Map.ofEntries(Map.entry("count", 1)));
		} catch (Exception e) {
			cronitorClient.fail(System.getenv("CRONITOR_MONITOR_KEY"), e.getMessage(), Map.ofEntries(Map.entry("error_count", 1)));
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

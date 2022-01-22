package com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.impl;

import com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.CoreRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoreRestTemplateImpl implements CoreRestTemplate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String execute(String url, HttpMethod httpMethod, String body) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity(body, headers);
		headers.set("x-apikey", System.getenv("x-apikey"));
		ResponseEntity<String> response = restTemplate.exchange(
				url,
				httpMethod,
				httpEntity,
				String.class
		);

		return response.getBody();
	}

}

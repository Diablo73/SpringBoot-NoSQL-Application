package com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.impl;

import com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.CoreRestTemplate;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplateHandler;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@Service
public class CoreRestTemplateImpl implements CoreRestTemplate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String execute(String url, HttpMethod httpMethod, String body, Map<String, String> addHeaders) {

		if (url.contains("{") && url.contains("}")) {
			restTemplate.setUriTemplateHandler(ignoreUriVariables());
		}

		HttpHeaders headers = createHttpHeaders(addHeaders);

		HttpEntity<String> httpEntity;
		if (Objects.isNull(body)) {
			httpEntity = new HttpEntity(headers);
		} else {
			httpEntity = new HttpEntity(body, headers);
		}

		ResponseEntity<String> response = restTemplate.exchange(
				url,
				httpMethod,
				httpEntity,
				String.class
		);

		return response.getBody();
	}

	private HttpHeaders createHttpHeaders(Map<String, String> addHeaders) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-apikey", System.getenv("X_API_KEY"));

		if (MapUtils.isNotEmpty(addHeaders)) {
			for (String headerName : addHeaders.keySet()) {
				headers.set(headerName, addHeaders.get(headerName));
			}
		}

		return headers;
	}

	private UriTemplateHandler ignoreUriVariables() {

		return new UriTemplateHandler() {
			@Override
			public URI expand(String uriTemplate, Object... uriVariables) {
				return retrieveURI(uriTemplate);
			}

			@Override
			public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
				return retrieveURI(uriTemplate);
			}

			private URI retrieveURI(String uriTemplate) {
				return UriComponentsBuilder.fromUriString(uriTemplate).build().toUri();
			}
		};
	}

}

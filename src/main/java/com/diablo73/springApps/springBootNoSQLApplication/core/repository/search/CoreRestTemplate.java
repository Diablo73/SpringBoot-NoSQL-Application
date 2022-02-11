package com.diablo73.springApps.springBootNoSQLApplication.core.repository.search;

import org.springframework.http.HttpMethod;

import java.util.Map;

public interface CoreRestTemplate {

	String execute(String url, HttpMethod httpMethod, String body, Map<String, String> addHeaders);
}

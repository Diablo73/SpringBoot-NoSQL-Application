package com.diablo73.springApps.springBootNoSQLApplication.core.repository.search;

import org.springframework.http.HttpMethod;

public interface CoreRestTemplate {

	String execute(String url, HttpMethod httpMethod, String body);
}

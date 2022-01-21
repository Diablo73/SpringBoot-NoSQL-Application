package com.diablo73.springApps.springBootNoSQLApplication.constants;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum APIConstantEnum {

	BLANK("", APIPathConstants.BLANK, HttpMethod.GET),
	DEFAULT_MESSAGE("default", APIPathConstants.DEFAULT_MESSAGE, HttpMethod.GET),
	RECORD_BY_DOCUMENT_ID("record.documentId", APIPathConstants.RECORD_BY_DOCUMENT_ID, HttpMethod.POST),


	;


	private final String apiName;
	private final String apiPath;
	private final HttpMethod httpMethod;


	APIConstantEnum(String apiName, String apiPath, HttpMethod httpMethod) {
		this.apiName = apiName;
		this.apiPath = apiPath;
		this.httpMethod = httpMethod;
	}
}

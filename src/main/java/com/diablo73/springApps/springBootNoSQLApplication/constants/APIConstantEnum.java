package com.diablo73.springApps.springBootNoSQLApplication.constants;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum APIConstantEnum {

	BLANK_GET("blank", APIPathConstants.BLANK, HttpMethod.GET),
	BLANK_POST("blank", APIPathConstants.BLANK, HttpMethod.POST),
	DEFAULT_MESSAGE_GET("default", APIPathConstants.DEFAULT_MESSAGE, HttpMethod.GET),
	DEFAULT_MESSAGE_POST("default", APIPathConstants.DEFAULT_MESSAGE, HttpMethod.POST),
	GET_RECORD_BY_DOCUMENT_ID("record.documentId", APIPathConstants.GET_RECORD_BY_DOCUMENT_ID, HttpMethod.POST),



	STUDENT_RECORD_BY_SEARCH_PARAMS("student.search", APIPathConstants.STUDENT_RECORD_BY_SEARCH_PARAMS, HttpMethod.POST),



	EMAIL_SEND("email.send", APIPathConstants.EMAIL_SEND, HttpMethod.POST),
	EMAIL_STATUS("email.status", APIPathConstants.EMAIL_STATUS, HttpMethod.POST),

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

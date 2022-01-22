package com.diablo73.springApps.springBootNoSQLApplication.constants.enums;

import lombok.Getter;

@Getter
public enum ResultInfoEnum {

	AUTH_CHECK_FAILED("0000", "INTRUDER", "Basic authentication failed!!!"),
	WRONG_FUNCTION("0001", "ILLEGAL_API", "Mismatch in API name and function!!!"),
	WRONG_DOCUMENT_ID("0002", "ILLEGAL_DOCUMENT_ID", "DocumentId provided is wrong!!!"),


	SUCCESS("1000", "SUCCESS", "Successful!!!"),

	;

	private final String resultCode;
	private final String resultStatus;
	private final String resultMessage;


	ResultInfoEnum(String resultCode, String resultStatus, String resultMessage) {
		this.resultCode = resultCode;
		this.resultStatus = resultStatus;
		this.resultMessage = resultMessage;
	}

}

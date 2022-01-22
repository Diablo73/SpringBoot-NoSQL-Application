package com.diablo73.springApps.springBootNoSQLApplication.constants.enums;

import lombok.Getter;

@Getter
public enum ResultInfoEnum {

	AUTH_CHECK_FAILED("0000", "INTRUDER", "Basic authentication failed!!!"),

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

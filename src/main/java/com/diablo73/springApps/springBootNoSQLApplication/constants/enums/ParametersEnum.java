package com.diablo73.springApps.springBootNoSQLApplication.constants.enums;

public enum ParametersEnum {

	DOCUMENT_ID("documentId"),
	TABLE_NAME("tableName"),

	;

	private final String name;

	ParametersEnum(String name) {
		this.name = name;
	}
}

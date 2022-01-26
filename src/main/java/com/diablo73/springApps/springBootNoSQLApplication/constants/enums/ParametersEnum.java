package com.diablo73.springApps.springBootNoSQLApplication.constants.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum ParametersEnum {

	DOCUMENT_ID("documentId", StringUtils.EMPTY),
	TABLE_NAME("tableName", StringUtils.EMPTY),
	ROLL_NO("rollNo", "roll_no"),
	GENDER("gender", "gender"),
	SECTION("section", "section"),



	STUDENTS_TABLE("studentsTable", StringUtils.EMPTY),
	MARKS_TABLE("marksTable", StringUtils.EMPTY),

	;

	private final String name;
	private final String field;
	private final String queryName;

	ParametersEnum(String name, String field) {
		this.name = name;
		this.field = field;
		this.queryName = getStringifyQuery(field);
	}

	public static ParametersEnum getEnumByName(String name) {
		for (ParametersEnum parametersEnum : ParametersEnum.values()) {
			if (parametersEnum.getName().equals(name)) {
				return parametersEnum;
			}
		}
		return null;
	}

	public static String getStringifyQuery(String s) {
		return "\"" + s + "\"";
	}
}

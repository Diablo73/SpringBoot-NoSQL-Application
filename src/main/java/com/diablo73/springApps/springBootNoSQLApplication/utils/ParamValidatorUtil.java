package com.diablo73.springApps.springBootNoSQLApplication.utils;

import com.diablo73.springApps.springBootNoSQLApplication.config.exceptions.ParamValidatorException;
import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstantEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.ParamConstants;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;

import java.util.*;

public class ParamValidatorUtil {


	public static void checkFunction(APIConstantEnum apiConstantEnum, String function) {

		if (!apiConstantEnum.getApiName().equals(function)) {
			throw new ParamValidatorException(ResultInfoEnum.WRONG_FUNCTION);
		}
	}

	public static void checkDocumentId(String documentId) {

		if (documentId.length() != ParamConstants.DOCUMENT_ID_LENGTH) {
			throw new ParamValidatorException(ResultInfoEnum.WRONG_DOCUMENT_ID);
		}
	}

	public static void checkSearchParams(Map<String, Object> searchParams) {

		if (searchParams.isEmpty()) {
			throw new ParamValidatorException(ResultInfoEnum.SEARCH_PARAM_EMPTY);
		}
		if (searchParams.keySet().stream().noneMatch(ParamConstants.ALLOWED_SEARCH_PARAMS::contains)) {
			throw new ParamValidatorException(ResultInfoEnum.WRONG_SEARCH_PARAM);
		}
	}

	public static void checkDate(Object... date) {

		for (Object o : date) {
			if (Objects.isNull(o)) {
				throw new ParamValidatorException(ResultInfoEnum.getResultInfoEnumWithMessage(
						ResultInfoEnum.DATE_PARSING_ERROR, "Date cannot be null!!!"));
			}
		}
	}

	public static void checkDatePattern(String pattern) {

		if (!DateUtil.PATTERN.contains(pattern)) {
			throw new ParamValidatorException(ResultInfoEnum.getResultInfoEnumWithMessage(
					ResultInfoEnum.DATE_PARSING_ERROR, "Pattern cannot be null!!!"));
		}
	}

	public static void checkStringNotEmpty(String s, String field) {
		if (s.isEmpty()) {
			throw new ParamValidatorException(ResultInfoEnum.getResultInfoEnumWithMessage(
					ResultInfoEnum.INVALID_STRING, field.concat(" cannot be empty!!!")
			));
		}
	}

	public static void checkListNotEmpty(List<?> list) {
		if (list.isEmpty()) {
			throw new ParamValidatorException(ResultInfoEnum.getResultInfoEnumWithMessage(
					ResultInfoEnum.INVALID_LIST, "List provided cannot be empty!!!"
			));
		}
	}
}

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
}

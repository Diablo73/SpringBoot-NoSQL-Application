package com.diablo73.springApps.springBootNoSQLApplication.template;

import com.diablo73.springApps.springBootNoSQLApplication.config.exceptions.ParamValidatorException;

public class APIProcessTemplateImpl {

	public static <REQUEST, RESULT, RESPONSE> RESPONSE execute(String apiName, APIProcessTemplate<REQUEST, RESULT, RESPONSE> processCallback) {

		RESPONSE response = null;

		try {
			processCallback.validate();

			REQUEST request = processCallback.convertRequest();

			RESULT result = processCallback.invoke(request);

			response = processCallback.convertResponse(result);

			response = processCallback.composeResultInfo();

		} catch (ParamValidatorException e) {

			response = processCallback.composeFailResultInfo(e.getResultInfoEnum());

		} catch (Exception e) {

		} finally {

		}
		return response;
	}
}

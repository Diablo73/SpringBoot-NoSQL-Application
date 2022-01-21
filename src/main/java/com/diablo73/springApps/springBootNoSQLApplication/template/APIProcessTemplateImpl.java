package com.diablo73.springApps.springBootNoSQLApplication.template;

public class APIProcessTemplateImpl {

	public static <REQUEST, RESULT, RESPONSE> RESPONSE execute(String apiName, APIProcessTemplate<REQUEST, RESULT, RESPONSE> processCallback) {

		RESPONSE response = null;

		try {
			REQUEST request = processCallback.convertRequest();

			RESULT result = processCallback.invoke(request);

			response = processCallback.convertResponse(result);

		} catch (Exception e) {

		} finally {

		}
		return response;
	}
}

package com.diablo73.springApps.springBootNoSQLApplication.template;

public interface APIProcessTemplate<REQUEST, RESULT, RESPONSE> {

	REQUEST convertRequest();

	RESULT invoke(REQUEST request);

	RESPONSE convertResponse(RESULT result);

}

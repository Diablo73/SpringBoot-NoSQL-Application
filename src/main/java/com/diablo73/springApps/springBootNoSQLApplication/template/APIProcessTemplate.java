package com.diablo73.springApps.springBootNoSQLApplication.template;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;

public interface APIProcessTemplate<REQUEST, RESULT, RESPONSE> {

	void validate();

	REQUEST convertRequest();

	RESULT invoke(REQUEST request);

	RESPONSE convertResponse(RESULT result);

	RESPONSE composeResultInfo(ResultInfoEnum resultInfoEnum);

}

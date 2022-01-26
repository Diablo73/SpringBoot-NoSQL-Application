package com.diablo73.springApps.springBootNoSQLApplication.constants;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParamConstants {

	public static final int DOCUMENT_ID_LENGTH 	= 24;
	public static final int ROLL_NO_LENGTH 		= 5;
	public static final List<String> ALLOWED_SEARCH_PARAMS = new ArrayList<>(
			Arrays.asList(
					ParametersEnum.ROLL_NO.getName(),
					ParametersEnum.GENDER.getName(),
					ParametersEnum.SECTION.getName()));
}

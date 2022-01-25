package com.diablo73.springApps.springBootNoSQLApplication.core.innerService;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;

import java.util.Map;

public interface CRUDInnerService {

	String get(Map<ParametersEnum, String> parameters);

}

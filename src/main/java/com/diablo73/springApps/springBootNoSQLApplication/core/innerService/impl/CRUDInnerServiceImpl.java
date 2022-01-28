package com.diablo73.springApps.springBootNoSQLApplication.core.innerService.impl;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;
import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.CRUDInnerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CRUDInnerServiceImpl extends CommonInnerServiceImpl implements CRUDInnerService {


	@Override
	public String get(Map<ParametersEnum, String> parameters) {

		StringBuilder url = new StringBuilder();
		if (STUDENTS_DB_NAME.equals(parameters.get(ParametersEnum.TABLE_NAME))) {
			url.append(STUDENTS_URL).append(STUDENTS_DB_NAME);
		} else if (MARKS_DB_NAME.equals(parameters.get(ParametersEnum.TABLE_NAME))) {
			url.append(MARKS_URL).append(MARKS_DB_NAME);
		}
		url.append(PATH_SEPARATOR).append(parameters.get(ParametersEnum.DOCUMENT_ID));

		return coreRestTemplate.execute(url.toString(), HttpMethod.GET, StringUtils.EMPTY);
	}
}

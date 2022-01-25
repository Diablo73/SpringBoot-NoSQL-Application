package com.diablo73.springApps.springBootNoSQLApplication.core.innerService.impl;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;
import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.CRUDInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.CoreRestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CRUDInnerServiceImpl implements CRUDInnerService {

	@Autowired
	private CoreRestTemplate coreRestTemplate;

	private static final String studentsURL = System.getenv("studentsURL");
	private static final String studentsDBName = System.getenv("studentsDBName");
	private static final String marksURL = System.getenv("marksURL");
	private static final String marksDBName = System.getenv("marksDBName");
	private static final String PATH_SEPARATOR = "/";


	@Override
	public String get(Map<ParametersEnum, String> parameters) {

		StringBuilder url = new StringBuilder();
		if (studentsDBName.equals(parameters.get(ParametersEnum.TABLE_NAME))) {
			url.append(studentsURL).append(studentsDBName);
		} else if (marksDBName.equals(parameters.get(ParametersEnum.TABLE_NAME))) {
			url.append(marksURL).append(marksDBName);
		}
		url.append(PATH_SEPARATOR).append(parameters.get(ParametersEnum.DOCUMENT_ID));

		return coreRestTemplate.execute(url.toString(), HttpMethod.GET, StringUtils.EMPTY);
	}
}

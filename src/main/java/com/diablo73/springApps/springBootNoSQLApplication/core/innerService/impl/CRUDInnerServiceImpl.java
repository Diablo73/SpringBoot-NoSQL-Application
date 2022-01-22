package com.diablo73.springApps.springBootNoSQLApplication.core.innerService.impl;

import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.CRUDInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.CoreRestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class CRUDInnerServiceImpl implements CRUDInnerService {

	@Autowired
	private CoreRestTemplate coreRestTemplate;

	private static final String userURL = System.getenv("userURL");
	private static final String userDBName = System.getenv("userDBName");
	private static final String PATH_SEPARATOR = "/";


	@Override
	public String get(String documentId) {

		String url = userURL
				+ userDBName
				+ PATH_SEPARATOR
				+ documentId;

		return coreRestTemplate.execute(url, HttpMethod.GET, StringUtils.EMPTY);
	}
}

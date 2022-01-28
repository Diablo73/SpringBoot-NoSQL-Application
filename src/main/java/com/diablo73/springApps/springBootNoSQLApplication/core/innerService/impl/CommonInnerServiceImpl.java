package com.diablo73.springApps.springBootNoSQLApplication.core.innerService.impl;

import com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.CoreRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonInnerServiceImpl {

	@Autowired
	protected CoreRestTemplate coreRestTemplate;

	protected static final String STUDENTS_URL = System.getenv("STUDENTS_URL");
	protected static final String STUDENTS_DB_NAME = System.getenv("STUDENTS_DB_NAME");
	protected static final String MARKS_URL = System.getenv("MARKS_URL");
	protected static final String MARKS_DB_NAME = System.getenv("MARKS_DB_NAME");
	protected static final String PATH_SEPARATOR = "/";
	protected static final String QUERY_SEPARATOR = "?q=";


}

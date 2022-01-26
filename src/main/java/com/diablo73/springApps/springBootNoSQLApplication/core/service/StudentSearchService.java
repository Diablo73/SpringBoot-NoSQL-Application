package com.diablo73.springApps.springBootNoSQLApplication.core.service;

import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;

public interface StudentSearchService {

	Response searchBySearchParams(Request request);
}

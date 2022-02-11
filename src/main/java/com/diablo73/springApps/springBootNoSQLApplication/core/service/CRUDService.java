package com.diablo73.springApps.springBootNoSQLApplication.core.service;

import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;

public interface CRUDService {

	Response get(Request request);

	Response post(Request request);

	Response put(Request request);

	Response delete(Request request);

}

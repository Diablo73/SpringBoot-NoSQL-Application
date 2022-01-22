package com.diablo73.springApps.springBootNoSQLApplication.core.service;

import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;

public interface CRUDService {

	public Response get(Request request);

	public Response post(Request request);

	public Response put(Request request);

	public Response delete(Request request);

}

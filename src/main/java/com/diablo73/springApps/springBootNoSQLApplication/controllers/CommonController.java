package com.diablo73.springApps.springBootNoSQLApplication.controllers;

import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResponseHead;

import java.util.Date;

public class CommonController {

	protected static void createResponseHead(Request request, Response response) {
		response.setHead(new ResponseHead());
		response.getHead().setFunction(request.getHead().getFunction());
		response.getHead().setResponseTime(String.valueOf(new Date()));
	}
}

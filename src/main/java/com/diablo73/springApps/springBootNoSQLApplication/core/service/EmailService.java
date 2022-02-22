package com.diablo73.springApps.springBootNoSQLApplication.core.service;

import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;

public interface EmailService {

	Response sendEmail(Request request);

	Response checkEmailStatus(Request request);
}

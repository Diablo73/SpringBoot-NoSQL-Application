package com.diablo73.springApps.springBootNoSQLApplication.controllers;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIPathConstants;
import com.diablo73.springApps.springBootNoSQLApplication.core.service.EmailService;
import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.diablo73.springApps.springBootNoSQLApplication.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmailController extends CommonController {

	@Autowired
	private EmailService emailService;


	@PostMapping(value = APIPathConstants.EMAIL_SEND, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> sendEmailController(@RequestBody Map<String, Object> requestMap) {

		Request request = (Request) MapperUtil.convertMap2Object(requestMap, Request.class);
		Response response = emailService.sendEmail(request);

		createResponseHead(request, response);

		return MapperUtil.convertObject2Map(response);
	}

	@PostMapping(value = APIPathConstants.EMAIL_STATUS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkEmailStatusController(@RequestBody Map<String, Object> requestMap) {

		Request request = (Request) MapperUtil.convertMap2Object(requestMap, Request.class);
		Response response = emailService.checkEmailStatus(request);

		createResponseHead(request, response);

		return MapperUtil.convertObject2Map(response);
	}

}

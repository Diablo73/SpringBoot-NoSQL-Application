package com.diablo73.springApps.springBootNoSQLApplication.controllers;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIPathConstants;
import com.diablo73.springApps.springBootNoSQLApplication.core.service.StudentSearchService;
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
public class StudentSearchController extends CommonController {

	@Autowired
	private StudentSearchService studentSearchService;


	@PostMapping(value = APIPathConstants.STUDENT_RECORD_BY_SEARCH_PARAMS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getDocumentsBySearchParamsController(@RequestBody Map<String, Object> requestMap) {

		Request request = (Request) MapperUtil.convert2RequestObject(requestMap, Request.class);
		Response response = studentSearchService.searchBySearchParams(request);

		createResponseHead(request, response);

		return MapperUtil.convert2ResponseMap(response);
	}
}

package com.diablo73.springApps.springBootNoSQLApplication.controllers;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIPathConstants;
import com.diablo73.springApps.springBootNoSQLApplication.core.service.CRUDService;
import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.diablo73.springApps.springBootNoSQLApplication.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class CRUDController extends CommonController {

	@Autowired
	private CRUDService crudService;


	@GetMapping({APIPathConstants.BLANK, APIPathConstants.DEFAULT_MESSAGE})
	public ResponseEntity<String> getDefaultMessage() {
		Date date = new Date();
		return new ResponseEntity<String>(
				"Welcome to the SpringBootNoSQLApplication!!!" +
						"<br>" +
						"Started Successfully at Time : " + date,
				HttpStatus.OK);
	}

	@PostMapping({APIPathConstants.BLANK, APIPathConstants.DEFAULT_MESSAGE})
	public ResponseEntity<String> postDefaultMessage() {
		Date date = new Date();
		return new ResponseEntity<String>(
				"Welcome to the SpringBootNoSQLApplication!!!" +
						"<br>" +
						"Started Successfully at Time : " + date,
				HttpStatus.OK);
	}


	@PostMapping(value = APIPathConstants.GET_RECORD_BY_DOCUMENT_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getDocumentByDocumentIdController(@RequestBody Map<String, Object> requestMap) {

		Request request = MapperUtil.convert2RequestObject(requestMap);
		Response response = crudService.get(request);

		createResponseHead(request, response);

		return MapperUtil.convert2ResponseMap(response);
	}

}

package com.diablo73.springApps.springBootNoSQLApplication.controllers;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstants;
import com.diablo73.springApps.springBootNoSQLApplication.constants.APIPathConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class Controller {


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
	

}

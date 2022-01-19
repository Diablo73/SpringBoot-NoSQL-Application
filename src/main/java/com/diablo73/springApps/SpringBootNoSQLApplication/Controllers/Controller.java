package com.diablo73.springApps.SpringBootNoSQLApplication.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Controller {

    @GetMapping("/")
    public ResponseEntity<String> defaultMessage() {
        Date date = new Date();
        return new ResponseEntity<String>(
                "Welcome to the SpringBootNoSQLApplication!!!" +
                        "<br>" +
                        "Started Successfully at Time : " + date,
                HttpStatus.OK);
    }

}

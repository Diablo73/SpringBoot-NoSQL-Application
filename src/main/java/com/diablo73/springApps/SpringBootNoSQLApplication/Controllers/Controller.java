package com.diablo73.springApps.SpringBootNoSQLApplication.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public ResponseEntity<String> defaultMessage() {
        return new ResponseEntity<String>("Welcome to the SpringBootNoSQLApplication Started Successfully!!!", HttpStatus.OK);
    }

}

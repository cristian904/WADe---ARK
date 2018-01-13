package ro.ark.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	 @RequestMapping("/helloworld")
	 public ResponseEntity<String> hello() {
	 
	  String helloWorldMessage = "Hello world from java2blog!";
	  return new ResponseEntity<>(helloWorldMessage, HttpStatus.OK);
	 }
}

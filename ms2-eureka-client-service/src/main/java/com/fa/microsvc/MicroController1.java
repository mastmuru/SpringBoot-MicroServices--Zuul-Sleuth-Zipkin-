/**
 * 
 */
package com.fa.microsvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/")
public class MicroController1 {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsersServiceImpl impl;

	String url = "http://zuul-api-gateway/api/micro2/";

	@HystrixCommand(commandKey = "micro1-key", fallbackMethod = "getUsersException", groupKey = "micro1-key")
	@GetMapping(value = "/getUserData")
	public ResponseEntity<?> getUserData() {
		logger.info("start");
		logger.info("end");
		return ResponseEntity.ok().body(impl.getAllUserInfo());
	}

	@HystrixCommand(commandKey = "micro1-key", fallbackMethod = "employeeException", groupKey = "micro1-key")
	@GetMapping(value = "/employees")
	public ResponseEntity<?> getUsers() {
		System.out.println(url);
		logger.info("start");
		logger.info("end");

		return ResponseEntity.ok().body(restTemplate.getForObject(url + "employees", Object.class));
	}

	public ResponseEntity<?> getUsersException() {
		logger.info("start");
		logger.info("end");

		return ResponseEntity.ok().body("Fall back get Users");
	}

	public ResponseEntity<?> employeeException() {
		logger.info("start");
		logger.info("end");

		return ResponseEntity.ok().body("Fall back get employees");
	}

}
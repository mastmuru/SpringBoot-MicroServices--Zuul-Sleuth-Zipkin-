/**
 * 
 */
package com.fa.microsvc.profile;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	EmployeeProfileServiceImpl employeeProfileService;

	String url = "http://zuul-api-gateway/api/micro1/";

	@PostMapping(value = "saveEmp")
	public void saveEmployeeProfile(@RequestBody EmployeeProfile employeeProfile) {
		logger.info("start");
		logger.info("end");
		employeeProfileService.addEmployeeProfile(employeeProfile);
	}

	@GetMapping(value = "employees")
	public List<EmployeeProfile> getAllEmployee() {
		logger.info("start");
		logger.info("end");
		return employeeProfileService.getEmployeeProfiles();
	}

//	@HystrixCommand(commandKey = "micro2-key", fallbackMethod = "getUserDataException", groupKey = "micro2-key")
	@GetMapping(value = "/getUserData")
	public ResponseEntity<?> getUsers() {
		logger.info("start");
		logger.info("end");
		return ResponseEntity.ok().body(restTemplate.getForObject(url + "getUserData", Object.class));
	}

	public String getUserDataException() {
		return "Fall back get Data";
	}
}
/**
 * 
 */
package com.fa.microsvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muruganandam
 *
 */
@RestController
@RequestMapping(value = "/proxy/")
public class GatewayController {

	@GetMapping(value = "test")
	public String test() {
		return "Welcome to Zuul API Gateway";
	}
}

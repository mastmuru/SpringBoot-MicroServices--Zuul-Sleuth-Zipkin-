package com.fa.microsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Ms2EurekServerSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms2EurekServerSvcApplication.class, args);
	}

}

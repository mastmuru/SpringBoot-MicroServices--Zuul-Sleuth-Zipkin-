package com.fa.microsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Ms2ApiGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms2ApiGatewayZuulApplication.class, args);
	}

}

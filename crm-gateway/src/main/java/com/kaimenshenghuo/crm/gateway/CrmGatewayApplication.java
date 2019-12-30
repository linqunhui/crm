package com.kaimenshenghuo.crm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CrmGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmGatewayApplication.class, args);
	}

}

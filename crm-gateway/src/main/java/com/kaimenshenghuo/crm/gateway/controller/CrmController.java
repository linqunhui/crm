package com.kaimenshenghuo.crm.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class CrmController {
	
	@Value("${sleep:}")
	private Integer sleep;
	
	@RequestMapping("/hello")
	public void getBalance() {
		System.out.println("hello");
		System.out.println("request:sleep: " + sleep);
	}
	
}

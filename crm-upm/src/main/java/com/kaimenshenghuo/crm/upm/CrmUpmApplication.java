package com.kaimenshenghuo.crm.upm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages="com.kaimenshenghuo")
@MapperScan("com.kaimenshenghuo.crm.common.data.mapper")
@EnableSwagger2
@EnableDiscoveryClient
public class CrmUpmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmUpmApplication.class, args);
	}

}

package com.alibaba.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.nacos.console.config.ConfigConstants;


/**
 * @author nacos
 */
@SpringBootApplication(scanBasePackages="com.alibaba.nacos")
@ServletComponentScan
@EnableScheduling
public class CrmNacosApplication {

	public static void main(String[] args) {
		System.setProperty(ConfigConstants.TOMCAT_DIR, "logs");
		System.setProperty(ConfigConstants.TOMCAT_ACCESS_LOG, "false");
		System.setProperty(ConfigConstants.STANDALONE_MODE, "true");
		SpringApplication.run(CrmNacosApplication.class, args);
	}

}

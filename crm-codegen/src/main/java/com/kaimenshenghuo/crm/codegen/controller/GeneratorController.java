package com.kaimenshenghuo.crm.codegen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaimenshenghuo.crm.codegen.entity.GenConfig;
import com.kaimenshenghuo.crm.codegen.service.GeneratorService;

import lombok.SneakyThrows;

@RestController
@RequestMapping("/generator")
public class GeneratorController {
	
	@Autowired
	private GeneratorService generatorService;
	
	@SneakyThrows
	@PostMapping("/code")
	public void code(@RequestBody GenConfig genConfig) {
		generatorService.code(genConfig);
	}
}

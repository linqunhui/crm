package com.kaimenshenghuo.crm.codegen.service;

import org.springframework.stereotype.Service;

import com.kaimenshenghuo.crm.codegen.entity.GenConfig;

@Service
public interface GeneratorService {

	public void code(GenConfig genConfig);
	
}

package com.kaimenshenghuo.crm.codegen.service.impl;

import com.kaimenshenghuo.crm.codegen.entity.GenConfig;
import com.kaimenshenghuo.crm.codegen.service.GeneratorService;
import com.kaimenshenghuo.crm.codegen.util.GenUtils;

public class GeneratorServiceImpl implements GeneratorService{
	

	@Override
	public void code(GenConfig genConfig) {
		GenUtils.generatorCode(genConfig);
	}

}

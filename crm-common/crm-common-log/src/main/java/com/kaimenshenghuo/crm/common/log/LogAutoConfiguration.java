package com.kaimenshenghuo.crm.common.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.kaimenshenghuo.crm.common.log.aspect.SysLogAspect;
import com.kaimenshenghuo.crm.common.log.event.SysLogListener;

import lombok.AllArgsConstructor;

@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {
	
//	private final RemoteLogService remoteLogService;
	
	@Bean
	public SysLogListener sysLogListener() {
		return null;
//		return new SysLogListener(remoteLogService);
	}

	@Bean
	public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher) {
		return new SysLogAspect(publisher);
	}

}

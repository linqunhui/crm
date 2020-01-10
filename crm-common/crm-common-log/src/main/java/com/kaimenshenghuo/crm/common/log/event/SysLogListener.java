package com.kaimenshenghuo.crm.common.log.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import com.kaimenshenghuo.crm.common.data.domain.Syslog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SysLogListener {
	
//	private final RemoteLogService remoteLogService;
	
	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		Syslog sysLog = event.getSyslog();
//		remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
	}

}

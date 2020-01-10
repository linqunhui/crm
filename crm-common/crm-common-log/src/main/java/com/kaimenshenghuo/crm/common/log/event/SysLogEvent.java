package com.kaimenshenghuo.crm.common.log.event;


import com.kaimenshenghuo.crm.common.data.domain.Syslog;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author lqh
 * 	系统日志事件
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {
	private final Syslog syslog;
}

package com.kaimenshenghuo.crm.common.job.config;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;


/**
 * @author ：linqunhui
 */
@Slf4j
@DisallowConcurrentExecution
public class CustomQuartzJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("执行定时任务");
	}

}

package com.kaimenshenghuo.crm.common.job.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务配置
 *
 * @author ：linqunhui
 * @date
 */
@Slf4j
@Configuration
public class QuartzConfig {
	
	
	/**
     * 测试定时任务构建
     *
     * @return
     */
    @Bean
    public JobDetail testTaskJobDetail() {
        return JobBuilder.newJob(TestTask.class)
                .withIdentity(TestTask.class.getName())
                .storeDurably(true)
                .build();
    }

    /**
     * 测试定时任务配置
     *
     * @return
     */
    @Bean
    public Trigger testTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(testTaskJobDetail())
                .withIdentity(TestTask.class.getName())
                .withSchedule(scheduleBuilder)
                .build();
    }

    @DisallowConcurrentExecution
    private class TestTask extends QuartzJobBean {
        @Override
        protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            log.debug("执行测试定时任务");
        }
    }
}

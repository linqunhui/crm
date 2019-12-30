package com.kaimenshenghuo.crm.upm.aspect;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.kaimenshenghuo.crm.common.data.domain.Syslog;
import com.kaimenshenghuo.crm.common.log.aspect.MyLog;
import com.kaimenshenghuo.crm.security.service.SyslogService;
import com.kaimenshenghuo.crm.security.util.IpUtil;

@Aspect
@Component
public class SysLogAspect {
	@Autowired
    private SyslogService sysLogService;
 
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.kaimenshenghuo.crm.common.log.aspect.MyLog)")
    public void logPoinCut() {
    }
 
    //切面 配置通知
    @AfterReturning("logPoinCut()")
    @Transactional
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        Syslog sysLog = new Syslog();
 
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
 
        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }
 
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
 
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) authentication.getDetails();
        //获取用户名
        sysLog.setUsername(authentication.getName());
        //获取用户ip地址
//        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setIp(IpUtil.getIpAddr(request));
        sysLog.setCreateDate(Timestamp.from(Instant.now()));
        //调用service保存SysLog实体类到数据库
        sysLogService.save(sysLog);
    }

}

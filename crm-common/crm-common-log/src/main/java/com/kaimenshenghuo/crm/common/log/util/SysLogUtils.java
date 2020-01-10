package com.kaimenshenghuo.crm.common.log.util;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kaimenshenghuo.crm.common.data.domain.Syslog;

import cn.hutool.core.util.URLUtil;
import io.netty.handler.codec.http.HttpUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SysLogUtils {
	
	public Syslog getSyslog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		Syslog sysLog = new Syslog();
//		sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
//		sysLog.setType(CommonConstants.STATUS_NORMAL);
//		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
//		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
//		sysLog.setMethod(request.getMethod());
//		sysLog.setUserAgent(request.getHeader("user-agent"));
//		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
//		sysLog.setServiceId(getClientId());
		return sysLog;
	}
	
	/**
	 * 获取客户端
	 *
	 * @return clientId
	 */
	private String getClientId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication instanceof OAuth2Authentication) {
//			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
//			return auth2Authentication.getOAuth2Request().getClientId();
//		}
		return null;
	}

	/**
	 * 获取用户名称
	 *
	 * @return username
	 */
	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication.getName();
	}
	
	
}

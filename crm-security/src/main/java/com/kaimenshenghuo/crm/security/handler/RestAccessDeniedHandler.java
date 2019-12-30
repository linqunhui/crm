package com.kaimenshenghuo.crm.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/** 
* 权限不足自定403返回值 
* 
* @author linqunhui 
*/  
@Component  
public class RestAccessDeniedHandler implements AccessDeniedHandler {  
	@Override  
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {  
	httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");  
	httpServletResponse.setStatus(403);  
	}
}

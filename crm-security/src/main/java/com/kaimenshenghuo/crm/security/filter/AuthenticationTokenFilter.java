package com.kaimenshenghuo.crm.security.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kaimenshenghuo.crm.security.service.impl.UserDetailsServiceImpl;
import com.kaimenshenghuo.crm.security.util.TokenUtil;

/** 
* JWT Filter 
* 
* @author linqunhui 
*/  
@Component 
public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	@Resource  
	private UserDetailsServiceImpl userDetailsService;  
	@Resource  
	private TokenUtil tokenUtil;
	
	/**
	 * 获取验证token中的身份信息 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//从请求头获取token
		String authHeader = request.getHeader("Authorization");
		//token前缀
		String tokenHead = "Bearer";
		if (authHeader != null && authHeader.startsWith(tokenHead)) {
			//去掉token前缀  
			String authToken = authHeader.substring(tokenHead.length());
			//从token中获取用户名  
			String username = tokenUtil.getUsernameFromToken(authToken);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = null;
				//普通用户  
				userDetails = userDetailsService.loadUserByUsername(username);
				if (tokenUtil.validateToken(authToken, userDetails)) {
					//token中的用户信息和数据库中的用户信息对比成功后将用户信息加入SecurityContextHolder相当于登陆
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}

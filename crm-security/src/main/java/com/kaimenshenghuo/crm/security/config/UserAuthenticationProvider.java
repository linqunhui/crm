package com.kaimenshenghuo.crm.security.config;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/** 
* 实现User专用的AuthenticationProvider 
* 选择实现DaoAuthenticationProvider是因为比较方便且能用 
* @author linqunhui
*/  
public class UserAuthenticationProvider extends DaoAuthenticationProvider{
	/** 
	* 初始化 将使用User专用的userDetailsService 
	* @param encoder 
	* @param userDetailsService 
	*/  
	public UserAuthenticationProvider(PasswordEncoder encoder, UserDetailsService userDetailsService){  
	setPasswordEncoder(encoder);  
	setUserDetailsService(userDetailsService);  
	}  
	  
	@Override  
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {  
	super.setPasswordEncoder(passwordEncoder);  
	}  
	  
	@Override  
	public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {  
	super.setUserDetailsPasswordService(userDetailsPasswordService);  
	}  
	  
	/** 
	* 判断只有传入UserAuthenticationToken的时候才使用这个Provider 
	* supports会在AuthenticationManager层被调用 
	* @param authentication 
	* @return 
	*/  
	public boolean supports(Class<?> authentication) {  
	return UserAuthenticationToken.class.isAssignableFrom(authentication);  
	}
}

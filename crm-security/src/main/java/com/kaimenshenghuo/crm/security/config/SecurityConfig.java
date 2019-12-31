package com.kaimenshenghuo.crm.security.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kaimenshenghuo.crm.security.filter.AuthenticationTokenFilter;
import com.kaimenshenghuo.crm.security.handler.EntryPointUnauthorizedHandler;
import com.kaimenshenghuo.crm.security.handler.RestAccessDeniedHandler;


/** 
* Security 配置 
* @author linqunhui 
*/  
@Configuration  
@EnableWebSecurity  
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired  
	@Qualifier("userDetailsService")  
	private UserDetailsService userDetailsService;
	
	@Resource  
	private AuthenticationTokenFilter authenticationTokenFilter;  
	@Resource  
	private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;  
	@Resource  
	private RestAccessDeniedHandler restAccessDeniedHandler;
	
	/** 
	* 注入UserAuthenticationProvider 
	* @return 
	*/  
	@Bean("UserAuthenticationProvider")  
	DaoAuthenticationProvider daoUserAuthenticationProvider(){  
		return new UserAuthenticationProvider(encoder(), userDetailsService);  
	}
	
	/** 
	* 向AuthenticationManager添加Provider 
	* @return 
	*/  
	@Autowired  
	public void configureGlobal(AuthenticationManagerBuilder auth){  
		auth.authenticationProvider(daoUserAuthenticationProvider());  
	}
	
	@Autowired  
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {  
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(encoder());  
	}
	/** 
	* 注入AuthenticationManager 
	* @return 
	*/  
	@Bean  
	public AuthenticationManager authenticationManagerBean() throws Exception {  
		return super.authenticationManagerBean();  
	}
	
	/** 
	* 注入PasswordEncoder 
	* @return 
	*/  
	@Bean  
	public PasswordEncoder encoder() {  
		PasswordEncoder encoder =  PasswordEncoderFactories.createDelegatingPasswordEncoder();  
		return encoder;  
	}
	
	/** 
	* 具体Security 配置 
	* @return 
	*/  
	@Override  
	protected void configure(HttpSecurity http) throws Exception {  
		http.  
		csrf().disable().//默认开启，这里先显式关闭csrf  
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext  
		.and()  
		.authorizeRequests()  
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //任何用户任意方法可以访问/**
		// swagger start
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/swagger-resources/**").permitAll()
        .antMatchers("/images/**").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/v2/api-docs").permitAll()
        .antMatchers("/configuration/ui").permitAll()
        .antMatchers("/configuration/security").permitAll()
        // swagger end
		.antMatchers("/user/login").permitAll() //任何用户可以访问/user/**  
		.anyRequest().authenticated() //任何没有匹配上的其他的url请求，只需要用户被验证  
		.and()  
		.headers().cacheControl();  
		http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);  
		http.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);  
	}
}

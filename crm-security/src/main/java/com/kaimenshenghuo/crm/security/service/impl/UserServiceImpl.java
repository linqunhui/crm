package com.kaimenshenghuo.crm.security.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaimenshenghuo.crm.common.data.domain.User;
import com.kaimenshenghuo.crm.common.data.mapper.UserMapper;
import com.kaimenshenghuo.crm.security.config.UserAuthenticationToken;
import com.kaimenshenghuo.crm.security.service.UserService;
import com.kaimenshenghuo.crm.security.util.TokenUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linqunhui
 * @since 2019-11-27
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);  
	  
	@Resource  
	private AuthenticationManager authenticationManager;  
	@Autowired  
	@Qualifier("userDetailsService")  
	private UserDetailsService userDetailsService;  
	@Resource  
	private TokenUtil tokenUtil;
	
	@Override
	public User login(User user) {
		//不同的用户类型使用不同的登陆方式  
		String token = "";  
		UserDetails userDetails = null;
		//登录  
		login(new UserAuthenticationToken(user.getUsername(), user.getPassword())); 
		userDetails = userDetailsService.loadUserByUsername(user.getUsername());  
		token = tokenUtil.generateToken(userDetails);  
		logger.info("user[{}]登陆成功",user.getUsername());  
		User userToken = new User();  
		userToken.setToken(token);  
		userToken.setUsername(((UserDetails)userDetails).getUsername());  
		return userToken == null ? new User() : userToken;
	}
	
	/** 
	* 校验账号密码并进行登陆 
	* @param upToken 
	*/  
	private void login(UsernamePasswordAuthenticationToken upToken){  
		//验证  
		Authentication authentication = authenticationManager.authenticate(upToken);  
		//将用户信息保存到SecurityContextHolder=登陆
		SecurityContextHolder.getContext().setAuthentication(authentication);  
	}

}

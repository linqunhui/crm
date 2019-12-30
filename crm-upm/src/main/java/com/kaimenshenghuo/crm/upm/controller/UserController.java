package com.kaimenshenghuo.crm.upm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaimenshenghuo.crm.common.data.domain.User;
import com.kaimenshenghuo.crm.common.log.aspect.MyLog;
import com.kaimenshenghuo.crm.security.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linqunhui
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户控制器")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/add")
	@ApiOperation(value = "保存新用户!", notes = "只保存一个用户!")
	public void addUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()).trim());
		userService.save(user);
		System.out.println("保存成功");
	}
	
	@GetMapping("/hello")
	@ApiOperation(value = "测试!", notes = "测试!")
	@MyLog(value = "进入hello")  //这里添加了AOP的自定义注解
	public String hello() {
		System.out.println("hello");
		return "111111";
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "登录!", notes = "登录!")
	@MyLog(value = "用户登录")  //这里添加了AOP的自定义注解
	public User login(@RequestBody User user) {
		System.out.println("登录");
		return userService.login(user);
	}
}

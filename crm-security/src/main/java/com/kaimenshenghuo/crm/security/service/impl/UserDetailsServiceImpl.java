package com.kaimenshenghuo.crm.security.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaimenshenghuo.crm.common.data.domain.Role;
import com.kaimenshenghuo.crm.common.data.domain.User;
import com.kaimenshenghuo.crm.common.data.domain.UserRole;
import com.kaimenshenghuo.crm.security.service.RoleService;
import com.kaimenshenghuo.crm.security.service.UserRoleService;
import com.kaimenshenghuo.crm.security.service.UserService;
import com.kaimenshenghuo.crm.security.util.KMUserDetails;


/** 
* User专用的UserDetailsService 
* @author linqunhui 
* 
*/
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Resource  
	private UserService userService;  
	@Resource  
	private UserRoleService userRoleService; 
	@Resource  
	private RoleService roleService; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
		//获取用户信息  
		User user = userService.getOne(queryWrapper);
		//获取角色列表 
		QueryWrapper<UserRole> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("user_id", user.getId());
		List<UserRole> roles = userRoleService.list(queryWrapper1);
		List<Long> idList = roles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
		Collection<Role> listByIds = roleService.listByIds(idList);
		List<String> rolesStr = listByIds.stream().map(Role::getName).collect(Collectors.toList());
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {  
			return new KMUserDetails(user.getId(),user.getUsername(), user.getPassword(), rolesStr.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));  
		}  
	}

}

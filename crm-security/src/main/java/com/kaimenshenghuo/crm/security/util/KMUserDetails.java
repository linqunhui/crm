package com.kaimenshenghuo.crm.security.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class KMUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户id  
	private Long userId;  
	//用户名  
	private String username;  
	//密码  
	private String password;  
	//用户角色表  
	private Collection<? extends GrantedAuthority> authorities;
	
	public KMUserDetails(Long userId,String username, String password, Collection<? extends GrantedAuthority> authorities){  
		this.userId = userId;  
		this.username = username;  
		this.password = password;  
		this.authorities = authorities;  
	}  
	
	
	/** 
	* 获取权限列表 
	* @return Collection 
	*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/** 
	* 获取用户Id 
	* @return String 
	*/  
	public Long getUserId() {  
		return userId;  
	}
	
	/** 
	* 获取密码 
	* @return String 
	*/  
	@Override
	public String getPassword() {
		return password;
	}

	/** 
	* 获取用户名 
	* @return String 
	*/  
	@Override
	public String getUsername() {
		return username;
	}
	
	/** 
	* 账号是否未过期 
	* @return boolean 
	*/
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/** 
	* 账号是否未锁定 
	* @return boolean 
	*/
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/** 
	* 凭证是否未过期 
	* @return boolean 
	*/
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/** 
	* 账号是否已启用 
	* @return boolean 
	*/
	@Override
	public boolean isEnabled() {
		return true;
	}

}

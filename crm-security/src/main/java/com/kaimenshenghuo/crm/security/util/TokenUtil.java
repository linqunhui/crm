package com.kaimenshenghuo.crm.security.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/** 
* JWT工具类 
* 
* @author linqunhui 
*/  
@Component
public class TokenUtil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	* 密钥 
	*/  
	private final String secret = "secret";
	
	/** 
	* 从数据声明生成令牌 
	* 
	* @param claims 数据声明 
	* @return 令牌 
	*/  
	private String generateToken(Map<String, Object> claims) {  
	//有效时间
	Date expirationDate = new Date(System.currentTimeMillis() + 2592000L * 1000);  
	return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();  
	}  
	  
	/** 
	* 从令牌中获取数据声明 
	* 
	* @param token 令牌 
	* @return 数据声明 
	*/  
	private Claims getClaimsFromToken(String token) {  
	Claims claims;  
	try {  
	claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();  
	} catch (Exception e) {  
	claims = null;  
	}  
	return claims;  
	}  
	  
	/** 
	* 生成令牌 
	* 
	* @param userDetails 用户 
	* @return 令牌 
	*/  
	public String generateToken(UserDetails userDetails) {  
	Map<String, Object> claims = new HashMap<>(2);  
	claims.put("sub", userDetails.getUsername());  
	claims.put("userId", ((KMUserDetails)userDetails).getUserId());  
	claims.put("created", new Date());  
	return generateToken(claims);  
	}  
	  
	/** 
	* 从令牌中获取用户名 
	* 
	* @param token 令牌 
	* @return 用户名 
	*/  
	public String getUsernameFromToken(String token) {  
	String username;  
	try {  
	Claims claims = getClaimsFromToken(token);  
	username = claims.getSubject();  
	} catch (Exception e) {  
	username = null;  
	}  
	return username;  
	}  
	  
	/** 
	* 从令牌中获取用户类型 
	* 
	* @param token 令牌 
	* @return 用户类型 
	*/  
	public String getUserTypeFromToken(String token) {  
	String userType;  
	try {  
	Claims claims = getClaimsFromToken(token);  
	userType = (String) claims.get("userType");  
	} catch (Exception e) {  
	userType = null;  
	}  
	return userType;  
	}  
	  
	/** 
	* 从令牌中获取用户Id 
	* 
	* @param token 令牌 
	* @return 用户Id 
	*/  
	public Integer getUserIdFromToken(String token) {  
	Integer userId;  
	try {  
	Claims claims = getClaimsFromToken(token);  
	userId = (Integer) claims.get("userId");  
	} catch (Exception e) {  
	userId = null;  
	}  
	return userId;  
	}  
	  
	/** 
	* 判断令牌是否过期 
	* 
	* @param token 令牌 
	* @return 是否过期 
	*/  
	public Boolean isTokenExpired(String token) {  
	try {  
	Claims claims = getClaimsFromToken(token);  
	Date expiration = claims.getExpiration();  
	return expiration.before(new Date());  
	} catch (Exception e) {  
	return false;  
	}  
	}  
	  
	/** 
	* 刷新令牌 
	* 
	* @param token 原令牌 
	* @return 新令牌 
	*/  
	public String refreshToken(String token) {  
	String refreshedToken;  
	try {  
	Claims claims = getClaimsFromToken(token);  
	claims.put("created", new Date());  
	refreshedToken = generateToken(claims);  
	} catch (Exception e) {  
	refreshedToken = null;  
	}  
	return refreshedToken;  
	}  
	  
	/** 
	* 验证令牌 
	* 
	* @param token       令牌 
	* @param userDetails 用户 
	* @return 是否有效 
	*/  
	public Boolean validateToken(String token, UserDetails userDetails) {  
	KMUserDetails user = (KMUserDetails) userDetails;  
	String username = getUsernameFromToken(token);  
	return (username.equals(user.getUsername()) && !isTokenExpired(token));  
	}
	
}

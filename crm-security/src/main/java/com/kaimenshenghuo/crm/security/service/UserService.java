package com.kaimenshenghuo.crm.security.service;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaimenshenghuo.crm.common.data.domain.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linqunhui
 * @since 2019-11-27
 */
@Component
public interface UserService extends IService<User> {
	
	public User login(User user);
}

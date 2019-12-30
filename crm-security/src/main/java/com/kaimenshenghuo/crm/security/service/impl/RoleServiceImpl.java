package com.kaimenshenghuo.crm.security.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaimenshenghuo.crm.common.data.domain.Role;
import com.kaimenshenghuo.crm.common.data.mapper.RoleMapper;
import com.kaimenshenghuo.crm.security.service.RoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linqunhui
 * @since 2019-11-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

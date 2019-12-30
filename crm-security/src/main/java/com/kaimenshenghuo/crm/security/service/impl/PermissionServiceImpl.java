package com.kaimenshenghuo.crm.security.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaimenshenghuo.crm.common.data.domain.Permission;
import com.kaimenshenghuo.crm.common.data.mapper.PermissionMapper;
import com.kaimenshenghuo.crm.security.service.PermissionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linqunhui
 * @since 2019-11-27
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}

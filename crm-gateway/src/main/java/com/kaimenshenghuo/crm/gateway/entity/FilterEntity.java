package com.kaimenshenghuo.crm.gateway.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

/**
 *
 * 过滤器实体类
 */
@Data
public class FilterEntity {

    //过滤器对应的Name
    private String name;

    //路由规则
    private Map<String, String> args = new LinkedHashMap<>();
}
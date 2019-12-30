package com.kaimenshenghuo.crm.gateway.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

/**
 *
 * 路由断言实体类
 */
@Data
public class PredicateEntity {

    //断言对应的Name
    private String name;

    //断言规则
    private Map<String, String> args = new LinkedHashMap<>();
}

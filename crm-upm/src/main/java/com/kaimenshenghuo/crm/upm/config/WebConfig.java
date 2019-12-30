package com.kaimenshenghuo.crm.upm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Author: linqunhui
 * Description: springBoot2.x已经废弃了WebMvcConfigurerAdapter这个类, 2.x版本后直接实现WebMvcConfigurer接口
 **/
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    // 省略了部分无关代码
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
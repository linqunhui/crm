package com.kaimenshenghuo.crm.upm.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//import org.springframework.web.reactive.config.ResourceHandlerRegistry;
//
//
///**
// * Author: linqunhui
// * Description: springBoot2.x已经废弃了WebMvcConfigurerAdapter这个类, 2.x版本后直接实现WebMvcConfigurer接口
// **/
//@Configuration
//@EnableWebFlux
//public class WebConfig implements WebFluxConfigurer {
//    // 省略了部分无关代码
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//}
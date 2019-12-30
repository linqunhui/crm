package com.kaimenshenghuo.crm.gateway.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.kaimenshenghuo.crm.gateway.service.DynamicRoutingServiceImpl;

@Component
public class DynamicRouteServiceImplByNacos {
	
	private final Logger logger = LoggerFactory.getLogger(DynamicRouteServiceImplByNacos.class);
	
	private static final String DATA_ID = "gateway-routes.json";
	private static final String Group = "DEFAULT_GROUP";
	
	@Autowired
	private DynamicRoutingServiceImpl dynamicRoutingService;
	
	public DynamicRouteServiceImplByNacos(){
		
		dynamicRouteByNacosListener(DATA_ID,Group);
	}

	
	/**
     * 	监听Nacos Server下发的动态路由配置
     * @param dataId
     * @param group
     */
	private void dynamicRouteByNacosListener(String dataId, String group) {
		try {
			ConfigService configService = NacosFactory.createConfigService("127.0.0.1:8848");
			String content = configService.getConfig(dataId, group, 5000);
			System.out.println(content);
			configService.addListener(dataId, group, new Listener() {
				
				@Override
				public void receiveConfigInfo(String configInfo) {
					RouteDefinition definition = JSON.parseObject(configInfo,RouteDefinition.class);
					dynamicRoutingService.update(definition);
				}
				
				@Override
				public Executor getExecutor() {
					return null;
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
}

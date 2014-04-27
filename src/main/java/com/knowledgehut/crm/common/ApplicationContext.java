package com.knowledgehut.crm.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContext {

	private static ApplicationContext instance;
	
	@Autowired
	private org.springframework.context.ApplicationContext appContext;
	
	private ApplicationContext() {
		instance = this;
	}
	
	public static ApplicationContext getInstance() {
		return instance;
	}

	public Object getBean(String name) {
		return appContext.getBean(name);
	}
	
	public <T> T getBean(Class<T> type) {
		return appContext.getBean(type);
	}
	
	public <T> Map<String, T> getBeansOfType(Class<T> type){
		return appContext.getBeansOfType(type);
	}
	
	public Resource getResource(String location) {
		return appContext.getResource(location);
	}
}

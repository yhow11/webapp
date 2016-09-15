package com.inctool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.inctool.management.manager.WorkerManager;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class ManagerContext {
	
	@Autowired
	private ServiceContext serviceContext;
	
	@Autowired
	private MapperContext mapperContext;
	
	@Bean
	public WorkerManager workerManager() throws Exception{
		return new WorkerManager(serviceContext.incWorkerService(), mapperContext.workerMapper());
	}
	
}

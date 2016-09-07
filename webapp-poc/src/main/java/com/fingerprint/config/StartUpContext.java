package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;

import com.google.common.base.Strings;

import helper.phoenix.dao.impl.SimplePhoenixDaoImpl;

@Configuration
@PropertySource({"classpath:com/fingerprint/properties/schema.properties"})
public class StartUpContext implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${auto.create.table.class}")
	private String tableClasses;
	
	@Autowired
	private SimplePhoenixDaoImpl simplePhoenixDaoImpl;
	
	private static boolean isTablesCreated = false;
	
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try{
			if(!Strings.isNullOrEmpty(tableClasses) && !isTablesCreated){
				for(String clazzName: tableClasses.split(",")){
					simplePhoenixDaoImpl.createTable(Class.forName(clazzName));
				}
				isTablesCreated = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	};
}

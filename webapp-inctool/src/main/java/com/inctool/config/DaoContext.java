package com.inctool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class DaoContext {
	
}

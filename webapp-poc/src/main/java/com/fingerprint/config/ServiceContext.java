package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fingerprint.event.service.EventService;
import com.fingerprint.event.service.impl.EventServiceImpl;
import com.fingerprint.management.service.JUserService;
import com.fingerprint.management.service.impl.UserDetailsServiceImpl;
import com.fingerprint.management.service.impl.UserServiceImpl;
import com.fingerprint.util.service.impl.KafkaService;

@Configuration
@PropertySource({"classpath:com/fingerprint/properties/application.properties",
"classpath:com/fingerprint/properties/kafka.properties"})
public class ServiceContext {
	

	@Value("${sync}")
	private String sync;

	@Value("${topic}")
	private String topic;

	@Autowired
	private DaoContext context;
	
	@Autowired
	private ConfigContext configContext;
	
	@Autowired
	private MapperContext mapperContext;
	
	@Bean
	public JUserService userService(){
		return new UserServiceImpl();
	}
	
	@Bean
	public UserDetailsService userDetailsService(){
		return new UserDetailsServiceImpl(userService());
	}
	
	@Bean
	public KafkaService kafkaService(){
		return new KafkaService(configContext.kafkaPropetiers(), sync, topic);
	}
	
	@Bean
	public EventService eventService() throws Exception{
		return new EventServiceImpl(context.eventDao());
	}
}

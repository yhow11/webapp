package com.nurtureretargeting.config;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import helper.spring.AppTemplateContext;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({SecurityContext.class, KafkaContext.class, PhoenixContext.class, WebSocketContext.class})
public class AppContext extends AppTemplateContext {

	
    public AppContext() throws IOException {
		super("application.properties","key.properties","url.properties","metric.properties","usertracker.properties","segment.properties");
		// TODO Auto-generated constructor stub
	}

	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
    	// TODO Auto-generated method stub
    	super.setApplicationContext(arg0);
//    	System.out.println(arg0.getBeansOfType(Storage.class).keySet().size());
    }
    
}
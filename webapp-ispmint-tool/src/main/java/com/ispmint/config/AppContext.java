package com.ispmint.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource({"classpath:com/ispmint/properties/application.properties",
	"classpath:com/ispmint/properties/podio.properties"
	})
public class AppContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
    	PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
    	Resource[] resources = new ClassPathResource[ ]
    	{ new ClassPathResource( "com/ispmint/properties/mail.properties" ) };
    	pspc.setLocations( resources );
    	pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
    }
}
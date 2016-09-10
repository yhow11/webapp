package com.nurtureretargeting.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nurtureretargeting.ScanMarker;
import com.nurtureretargeting.common.interceptor.AccessInterceptor;


@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackageClasses= ScanMarker.class, includeFilters={ @ComponentScan.Filter(Controller.class) } )
public class WebAppContext extends WebMvcConfigurerAdapter {
	

	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver url = new InternalResourceViewResolver();
		url.setPrefix("/WEB-INF/");
		url.setSuffix(".jsp");
		return url;
	}


	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:com/nurtureretargeting/messages.properties");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.springframework.dao.DataAccessException", "error");
		b.setExceptionMappings(mappings);
		return b;
	}
	
	@Bean
	public AccessInterceptor accessInterceptor() {
		return new AccessInterceptor();
	}
	 @Override
     public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(accessInterceptor());
     }
	
}
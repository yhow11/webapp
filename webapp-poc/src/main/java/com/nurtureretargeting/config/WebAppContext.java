package com.nurtureretargeting.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nurtureretargeting.admin.keymanagement.controller.KeyManagementController;
import com.nurtureretargeting.admin.metricmanagement.controller.MetricController;
import com.nurtureretargeting.admin.metricmanagement.controller.MetricSummaryController;
import com.nurtureretargeting.admin.metricmanagement.controller.MetricTypeController;
import com.nurtureretargeting.admin.segmentmanagement.controller.SegmentController;
import com.nurtureretargeting.admin.segmentmanagement.controller.SegmentFilterController;
import com.nurtureretargeting.admin.segmentmanagement.controller.SegmentedVisitorController;
import com.nurtureretargeting.admin.urlmanagement.controller.URLImportController;
import com.nurtureretargeting.admin.urlmanagement.controller.URLTaggingController;
import com.nurtureretargeting.admin.visitormanagement.controller.ActiveVisitorController;
import com.nurtureretargeting.common.interceptor.AccessInterceptor;
import com.nurtureretargeting.site.controller.SiteController;
import com.nurtureretargeting.tracker.log.controller.LogsController;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages={"com.nurtureretargeting"}, useDefaultFilters=false, includeFilters={@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value={KeyManagementController.class,MetricController.class,MetricSummaryController.class,MetricTypeController.class,URLImportController.class,URLTaggingController.class,LogsController.class,SiteController.class,ActiveVisitorController.class,SegmentFilterController.class, SegmentController.class,SegmentedVisitorController.class })})
public class WebAppContext extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver url = new InternalResourceViewResolver();
		url.setPrefix("/WEB-INF/");
		url.setSuffix(".jsp");
		return url;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:application.properties");
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
	@Bean
	public Properties appProp() throws IOException{
		return PropertiesLoaderUtils.loadAllProperties("application.properties");
	}
	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
		PropertySourcesPlaceholderConfigurer source = new PropertySourcesPlaceholderConfigurer();
		source.setProperties(appProp());
		return source;
	}
}
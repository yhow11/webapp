package com.nurtureretargeting.config;

import javax.servlet.Filter;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppContext.class, SecurityContext.class, ServiceContext.class, ManagerContext.class, MapperContext.class, KafkaContext.class, DaoContext.class, WebSocketContext.class, PhoenixContext.class, CronContext.class, StartUpContext.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebAppContext.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new HiddenHttpMethodFilter() };
	}

}

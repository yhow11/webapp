package com.inctool.config;

import javax.servlet.Filter;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.inctool.config.AppContext;
import com.inctool.config.DaoContext;
import com.inctool.config.MapperContext;
import com.inctool.config.MongoContext;
import com.inctool.config.SecurityContext;
import com.inctool.config.ServiceContext;
import com.inctool.config.WebAppContext;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppContext.class, SecurityContext.class, ServiceContext.class, MapperContext.class, MongoContext.class, DaoContext.class};
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

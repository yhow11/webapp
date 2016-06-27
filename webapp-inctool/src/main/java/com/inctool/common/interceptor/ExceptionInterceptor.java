package com.inctool.common.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExceptionInterceptor {
	
	private static final Logger logger = Logger.getLogger(ExceptionInterceptor.class);
	
	@AfterThrowing(pointcut = "execution(* com.podio..* (..))", throwing = "ex")
	public void errorInterceptor(Exception ex) {
		if (logger.isDebugEnabled()) {
			logger.debug("Error Message Interceptor started");
		}

		// DO SOMETHING HERE WITH EX
		logger.debug(ex.getCause().getMessage());

		if (logger.isDebugEnabled()) {
			logger.debug("Error Message Interceptor finished.");
		}
	}
}
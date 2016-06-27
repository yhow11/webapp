package com.ispmint.common.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MethodInterceptor {

	private static final Logger logger = Logger.getLogger(MethodInterceptor.class);
	
	@Around("execution(* *(..))")
    public void interceptViews(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		logger.info("TEST");
		proceedingJoinPoint.proceed();
    }
}

package sparkapp.collation.receiver.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import common.LogMetaData;

@Aspect
@Component
public class MethodInterceptor {

	
	private static final Logger logger = Logger.getLogger(MethodInterceptor.class);
	
	@Around("execution(* *(..)) && @annotation(common.Loggable)")
    public Object interceptViews(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		for(Object arg: proceedingJoinPoint.getArgs()){
			if(arg != null && arg.getClass().equals(LogMetaData.class)){
				LogMetaData lmd = LogMetaData.class.cast(arg);
				lmd.getTransactions().add(proceedingJoinPoint.getTarget().getClass().getSimpleName()+"."+proceedingJoinPoint.getSignature().getName());
				logger.info(String.join("==>", lmd.getTransactions()));
			}
		}
		
		return proceedingJoinPoint.proceed();
    }
}

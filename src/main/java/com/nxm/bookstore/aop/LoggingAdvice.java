package com.nxm.bookstore.aop;

import java.text.MessageFormat;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAdvice {
	private static Logger logger=LoggerFactory.getLogger(LoggingAdvice.class);

	@Pointcut("@within(org.springframework.stereotype.Service)")
	public void bookService() {

	}

	@Before("bookService()")
	public void doSomethingBefore() {

	}

	@Around("bookService()")
	public Object doSomethingAround(ProceedingJoinPoint jp) {
		logger.trace("Entering method " + jp.getSignature().getName());
		long msBefore = System.currentTimeMillis();
		
		Object result = null;
		try {
			result = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		logger.trace("Takes " + (System.currentTimeMillis() - msBefore) + " ms to execute");
		return result;
	}
	
	@Around("execution(* org.slf4j.Logger.debug(..))")
    public Object encryptLog (ProceedingJoinPoint thisJoinPoint) throws Throwable{
         Object[] arguments = thisJoinPoint.getArgs();
         if(arguments[0] instanceof String){
             String encryptedLog = encryptLogMessage ((String) arguments[0], arguments.length > 1 ? Arrays.copyOfRange(arguments, 1, arguments.length) : null);
            arguments[0] = encryptedLog;
         }

        return thisJoinPoint.proceed(arguments);
    }
    private final String encryptLogMessage (String message, Object... args){
        if(args != null){
            return MessageFormat.format(message, args);
        }
        return message;
    }
}

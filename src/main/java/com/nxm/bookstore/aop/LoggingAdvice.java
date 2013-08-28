package com.nxm.bookstore.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAdvice {
	Logger logger = Logger.getLogger(LoggingAdvice.class);

	@Pointcut("@within(org.springframework.stereotype.Service)")
	public void bookService() {

	}

	@Before("bookService()")
	public void doSomethingBefore() {

	}

	@Around("bookService()")
	public Object doSomethingAround(ProceedingJoinPoint jp) {
		logger.info("Entering method " + jp.getSignature().getName());
		long msBefore = System.currentTimeMillis();
		
		Object result = null;
		try {
			result = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		logger.info("Takes " + (System.currentTimeMillis() - msBefore) + " ms to execute");
		return result;
	}
}

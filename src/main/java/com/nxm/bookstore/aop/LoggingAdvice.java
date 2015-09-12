package com.nxm.bookstore.aop;

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
}

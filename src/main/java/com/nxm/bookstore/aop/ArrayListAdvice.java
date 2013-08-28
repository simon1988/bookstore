package com.nxm.bookstore.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * This is only a dummy advice, cause spring aop supports only spring managed beans.
 * 
 * @author Simon
 *
 */
@Aspect
public class ArrayListAdvice {
	
	Logger logger = Logger.getLogger(ArrayListAdvice.class);
	
	@Pointcut("execution(* java.util.ArrayList.add(..))")
	public void arrayListAdd(){
		
	}
	
	@Around("arrayListAdd()")
	public Object doSomethingAround(ProceedingJoinPoint jp) {
		logger.info("Calling add in arraylist");
		Object result = null;
		try {
			result = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
}

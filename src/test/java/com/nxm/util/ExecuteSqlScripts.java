package com.nxm.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation aims to let you execute sql scripts before @Test methods.
 * Will roll back the scripts if methods are annotated with @Transactional.
 * 
 * @author xn91913
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) 
public @interface ExecuteSqlScripts {
	String[] value() default {};
}

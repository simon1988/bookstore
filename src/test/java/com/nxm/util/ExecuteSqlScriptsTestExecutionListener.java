package com.nxm.util;

import java.lang.reflect.Method;
import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.Assert;

public class ExecuteSqlScriptsTestExecutionListener extends AbstractTestExecutionListener {
	private static Logger logger=LoggerFactory.getLogger(ExecuteSqlScriptsTestExecutionListener.class);
	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		final Method testMethod = testContext.getTestMethod();
		Assert.notNull(testMethod, "test method must not be null");
		if(testMethod.isAnnotationPresent(ExecuteSqlScripts.class)){
			ExecuteSqlScripts sqlScripts = testMethod.getAnnotation(ExecuteSqlScripts.class);
			if(sqlScripts==null || sqlScripts.value()==null){
				return;
			}
			//Autowire the signal datasource, assume test context contains only 1 datasource :h2
			DataSource dataSource = testContext.getApplicationContext().getBean(DataSource.class);
			Connection connection = DataSourceUtils.getConnection(dataSource);
			ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			for(String filename: sqlScripts.value()){
				logger.debug("Prepare to execute " + filename + " before " + testMethod.getName());
				populator.addScript(testContext.getApplicationContext().getResource(filename));
			}
			try {
				populator.populate(connection);
			} finally{
				DataSourceUtils.releaseConnection(connection, dataSource);
			}
		}
	}
}

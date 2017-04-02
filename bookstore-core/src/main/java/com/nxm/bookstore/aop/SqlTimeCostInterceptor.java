package com.nxm.bookstore.aop;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class SqlTimeCostInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(SqlTimeCostInterceptor.class);
	private long thresholdInMs = 0;

	public Object intercept(Invocation invocation) throws Throwable {
		Object result = null;
		if ("query".equals(invocation.getMethod().getName()) || "update".equals(invocation.getMethod().getName())) {
			MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
			String statId = ms.getId();
			String sql = ms.getBoundSql(invocation.getArgs()[1]).getSql();
			long startTime = System.currentTimeMillis();

			result = invocation.proceed();

			long timeCost = System.currentTimeMillis() - startTime;
			if (timeCost >= this.thresholdInMs) {
				this.info("TimeCost[" + String.valueOf(timeCost).length() + "]" + "statement id : " + statId
						+ ", time cost : " + timeCost + ", raw sql : " + sql.replaceAll("\n", ""));
			}
		} else {
			result = invocation.proceed();
		}

		return result;
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	protected void info(String msg) {
		logger.info(msg);
	}

	@Override
	public void setProperties(Properties arg0) {
	}

	public long getThresholdInMs() {
		return thresholdInMs;
	}

	public void setThresholdInMs(long thresholdInMs) {
		this.thresholdInMs = thresholdInMs;
	}
}

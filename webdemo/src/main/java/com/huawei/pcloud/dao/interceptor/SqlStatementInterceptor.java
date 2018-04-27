package com.huawei.pcloud.dao.interceptor;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;
import com.google.gson.Gson;

/**
 * 数据库操作性能拦截器,记录耗时
 */
@Intercepts(value = {
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class SqlStatementInterceptor implements Interceptor {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		String sqlId = "";
		String sql = "";
		try {
			final Object[] args = invocation.getArgs();
			// 获取原始的ms
			MappedStatement ms = (MappedStatement) args[0];
			Object parameter = null;
			// 获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
			if (invocation.getArgs().length > 1) {
				parameter = invocation.getArgs()[1];
			}
			logger.info("invocation parameter : {}",new Gson().toJson(parameter));
			// 获取到节点的id,即sql语句的id
			sqlId = ms.getId();
			// BoundSql就是封装myBatis最终产生的sql类
			BoundSql boundSql = ms.getBoundSql(parameter);
			// 获取节点的配置
			Configuration configuration = ms.getConfiguration();
			// 获取到最终的sql语句
			sql = showSql(configuration, boundSql);
			logger.info("sqlId:{}; execuSql:{};", sqlId,sql);
		} catch (Exception e) {
			logger.error("mybatis sql intercept", e);
		}
		Object returnValue = invocation.proceed();
		long end = System.currentTimeMillis();
		long time = end - start;
		logger.info("sqlId:{}; execuSql:{}; lossTime:{}", sqlId,sql,time);
		return returnValue;
	}

	/**
	 * 如果参数是String，则添加单引号， 如果是日期，则转换为时间格式器并加单引号； 对参数是null和不是null的情况作了处理
	 * 
	 * @param obj
	 * @return
	 */
	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	/**
	 * 对sql语句中的?进行替换处理
	 * 
	 * @param configuration
	 * @param boundSql
	 * @return
	 */
	public static String showSql(Configuration configuration, BoundSql boundSql) {
		// 获取参数
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		// sql语句中多个空格都用一个空格代替
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
			// 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			// 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
			} else {
				// MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值
				// 主要支持对JavaBean、Collection、Map三种类型对象的操作
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						// 该分支是动态sql
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else {
						// 打印出缺失，提醒该参数缺失并防止错位
						sql = sql.replaceFirst("\\?", "null");
					}
				}
			}
		}
		return sql;
	}

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		
	}

}
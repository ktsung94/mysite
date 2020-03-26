package com.douzone.mysite.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:com/douzone/mysite/config/app/properties/jdbc.properties")
public class DBConfig {
	
	// @PropertySource("classpath:com/douzone/mysite/config/app/properties/jdbc.properties")
	// 이것이 안으로 들어옴
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getProperty("jdbc.DriverClassName"));
		basicDataSource.setUrl(env.getProperty("jdbc.Url"));
		basicDataSource.setUsername(env.getProperty("jdbc.Username"));
		basicDataSource.setPassword(env.getProperty("jdbc.Password"));
		basicDataSource.setInitialSize(env.getProperty("jdbc.InitialSize", Integer.class));
		basicDataSource.setMaxActive(env.getProperty("jdbc.MaxActive", Integer.class));
		return basicDataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}

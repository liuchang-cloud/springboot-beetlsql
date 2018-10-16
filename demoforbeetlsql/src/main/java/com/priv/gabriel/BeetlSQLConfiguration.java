package com.priv.gabriel;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-14
 * @Description:
 */
@Configuration
public class BeetlSQLConfiguration {
	/*
	 * @Author Gabriel
	 * @Description 设置数据源
	 * @Date 2018/10/16
	 * @Param [env] SpringBoot的运行时环境，用于获取配置文件中的值
	 * @Return javax.sql.DataSource
	 */
	@Bean(name = "datasource")
	public DataSource getDataSource(Environment env){
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}
}
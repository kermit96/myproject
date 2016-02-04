package com.spring.test.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
 @MapperScan(" com.spring.test")
 public class DataConfig {
 
     @Bean
     public DataSource dataSource() {
         
 		// super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+"localhost"+":"+1521+":"+"orcl",
//		userid,password,4
	 // 	);			   	 
    	 
    	 BasicDataSource dataSource = new BasicDataSource();
         dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");         
         dataSource.setUsername("scott");
         dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
         dataSource.setPassword("tiger");
 
         // create a table and populate some data
         return dataSource;
     }
 
     @Bean
     public DataSourceTransactionManager transactionManager() {
         return new DataSourceTransactionManager(dataSource());
     }
 
     @Bean
     public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
         SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
         sessionFactory.setDataSource(dataSource());
     //  sessionFactory.setTypeAliasesPackage("org.lanyonm.playground.domain");
         return sessionFactory;
     }
 }
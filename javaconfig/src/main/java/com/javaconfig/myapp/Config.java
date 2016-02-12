package com.javaconfig.myapp;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@MapperScan("com.javaconfig.myapp")
@Configuration
public class Config {
		
	   @Bean
	    public DataSource dataSource() {
		   
		   DataSource dataSource = new DataSource();
	        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	        dataSource.setUsername("scott");
	        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	        dataSource.setPassword("tiger");	     
	        return dataSource;
	    }
	     
	    @Bean
	    public DataSourceTransactionManager transactionManager()
	    {
	        return new DataSourceTransactionManager(dataSource());
	    }
	     

	
	 @Bean
	 public SqlSessionFactory sqlSessionFatory(DataSource datasource) throws Exception{
	  		 
	  SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	  sqlSessionFactory.setDataSource(datasource);
	  sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
	  return (SqlSessionFactory) sqlSessionFactory.getObject();
	 } 
	 
	 @Bean
	 public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		 
	
	  return new SqlSessionTemplate(sqlSessionFactory);
	 }
	 	 	 
}

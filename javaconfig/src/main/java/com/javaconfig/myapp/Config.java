package com.javaconfig.myapp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import iedu.config.Globalconfig;
import iedu.sql.DBTYPE;


@MapperScan("com.javaconfig.myapp")
@Configuration
public class Config {
		
	   @Bean
	    public DataSource dataSource() {
		   
		   Globalconfig config =  new Globalconfig();
		   
		
		   DataSource dataSource = new DataSource();
		   
		   String classname = "";
		   String url = "";
		   int port = config.getPort();
		   
		   switch(DBTYPE.fromInt(config.getDbtype())) 
		   {
		     case ORACLE_TYPE:
		    	  classname = "oracle.jdbc.driver.OracleDriver";
		    	  if (port == 0) {
		    		 port=1521;  
		    		 
		    	  }
		    	  url = String.format("jdbc:oracle:thin:@%s:%d:%s", config.getHost(),port,config.getDbname()) ;
			   break;  
			   
		     case MSSQL_TYPE:
		     	  classname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		     	 if (port == 0) {
		    		  
		    		  port =1433;
		    	  }
	    	     url = "jdbc:sqlserver://"+config.getHost()+":"+port+";databaseName="+config.getDbname();
				 break;   
				 
		     case  MYSQL_TYPE:
		    	 		  
		   	     classname = "com.mysql.jdbc.Driver";
	    	     if (port == 0) {		    		  
	    	    	 port =3306;
		    	  }
	    	     url = "jdbc:mysql://"+config.getHost()+":"+port+"/"+config.getDbname()+"?useSSL=false"	;
	
			    break;
				 
		   }
		   
	        dataSource.setDriverClassName(classname);
	        dataSource.setUsername(config.getUserid());
	        dataSource.setUrl(url);
	        dataSource.setPassword(config.getPassword());
	        
	        return dataSource;
	    }
	     
	    @Bean
	    public DataSourceTransactionManager transactionManager()
	    {
	        return new DataSourceTransactionManager(dataSource());
	    }
	     

	
	 @Bean
	 public SqlSessionFactory sqlSessionFatory(DataSource datasource,ApplicationContext applicationContext) throws Exception{
	  		 
	  SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	  sqlSessionFactory.setDataSource(datasource);

	//  sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/myBatis-config.xml"));
	  sqlSessionFactory.setConfigLocation(new ClassPathResource("/mybatis/config/myBatis-config.xml"));
	  //configLocation 
	  return (SqlSessionFactory) sqlSessionFactory.getObject();
	  
	 } 
	 
	 @Bean
	 public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		 
	    return new SqlSessionTemplate(sqlSessionFactory);
	 }
	 	 	 
}

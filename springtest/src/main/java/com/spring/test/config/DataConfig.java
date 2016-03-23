package com.spring.test.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan; 
 import org.springframework.beans.factory.InitializingBean; 
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
 import org.springframework.core.env.Environment; 
 import org.springframework.jdbc.datasource.DataSourceTransactionManager; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
  




@Configuration 
 @EnableTransactionManagement 
 @MapperScan(basePackages = "com.spring.test") 
 public class DataConfig implements InitializingBean  {
 
	 
	     @Autowired 
	     private Environment environment; 

	
	
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


     
     
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	} 

	
	  @Bean 
	      public SqlSessionFactoryBean sqlSessionFactoryBean( 
	              DataSource dataSource, ApplicationContext applicationContext) throws IOException { 
	  
	   
	          SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean(); 
	  
	   
	          // 마이바티스가 사용한 DataSource를 등록 
	          factoryBean.setDataSource(dataSource); 
	  
	   
	           // 마이바티스 설정파일 위치 설정 
	           factoryBean.setConfigLocation(applicationContext.getResource("classpath:/com/spring/test/mybatis/myBatis-config.xml")); 
	   
	   // DB2, Derby, H2, HSQL, Informix, MS-SQL, MySQL, Oracle, PostgreSQL, Sybase
	           
	           
	           
	           // spring.examples.model 패키지 이하의 model 클래스 이름을 짧은 별칭으로 등록 
	//          factoryBean.setTypeAliasesPackage("spring.examples.model"); 
	   
	   
	           // META-INF/mybatis/mappers 패키지 이하의 모든 XML을 매퍼로 등록 
//	           factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mappers/**/*.xml")); 
	  
	   
	           return factoryBean; 
	      } 
	  
	   
	      /** 
	        * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다. 
	        * 
	        * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다. 
	        * 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다. 
	        */ 
	       @Bean 
	       public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) { 
	           return new SqlSessionTemplate(sqlSessionFactory); 
	       }           
 }
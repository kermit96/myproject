package Dao;
import java.sql.*;
import java.util.*;
import org.apache.commons.dbcp2.BasicDataSource;

public class MyJDBCDao {

	
	// db pool ���� 
	BasicDataSource ds = new BasicDataSource();
	Connection con = null;
	
	HashSet<Statement> statements = new HashSet<Statement>();
	HashSet<PreparedStatement>  prestatements = new HashSet<PreparedStatement>();
	HashSet<ResultSet>  resultsets = new HashSet<ResultSet>();
	
	public MyJDBCDao() throws  Exception  {
		// TODO Auto-generated constructor stub		
		try {					
			ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			ds.setUsername("scott");
			ds.setPassword("tiger");			
        	ds.setInitialSize(4);
        	
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			throw ex;
		}
				
		try { 
		
	   	con =   getcon();
		} catch (Exception ex) {
			throw ex;		
		}    	    			
	}
		
	public ResultSet GetResultStat(String sql) throws Exception
	{
		
		
		Statement  stat  =   getSTMT();
		
		ResultSet result= stat.executeQuery(sql);
		
		close(stat);

		resultsets.add(result);
		
		return result;
		
		
	}
	
	
	public Connection getcon() throws Exception   {
		
		if (con == null) {
										
		try { 

			con = ds.getConnection();
			} catch (Exception ex) {
				throw ex;		
			}
	    	
		}
		
     	return con;
	}
	

	public Statement getSTMT() throws Exception {
		
		Statement smt = null;
		try {
			smt = con.createStatement();			
			statements.add(smt);						
		} catch(Exception ex) {
			throw ex;					
		}
		
		return smt;
	}
	
	
	
	
	public PreparedStatement getPrepare(String sql) throws Exception {
		
		PreparedStatement smt = null;
		
		try {
			smt = con.prepareStatement(sql);
			this.prestatements.add(smt);			
		} catch(Exception ex) {
			throw ex;					
		}
		
		return smt;
		
	}
	
	public  void closeAll()
	{
		
		for(ResultSet set  :this.resultsets) {
			try { 
		       set.close();
			} catch (Exception ex) {
				
				
			}
		}
		
		resultsets.clear();
		
		for(Statement stat: statements) {
			try {
			  stat.close();
			} catch(Exception ex) {
				
				
			}
		}
		statements.clear();
		
		
		for(PreparedStatement stat: this.prestatements) {
			try {
			  stat.close();
			} catch(Exception ex) {
				
				
			}
		}
		
		prestatements.clear();
		
		close(con);
		con = null;
						
	}
	
	
	
	
	public  void close(Object obj) {		
		try {
			if (obj instanceof ResultSet ) {
				
				((ResultSet)(obj)).close();
				resultsets.remove(obj);							
			}
			
			if (obj instanceof Connection ) {
				
				((Connection)(obj)).close();
				if (obj== this.con) 
					con = null;
			}
			
			
			if (obj instanceof PreparedStatement ) {
				((PreparedStatement)(obj)).close();
				this.prestatements.remove(obj);
			}
			
			if (obj instanceof Statement ) {
				
				((Statement)(obj)).close();
				this.statements.remove(obj);
			}
						
		} catch (Exception ex) {
						
			
		}
		
	}
	
}

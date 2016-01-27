package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class PoolDB {
	private DataSource ds ;
	
	public PoolDB() {
		 // 1. Context.xml 에 등록한 리소스를 알아내기 위한 클래스를 준비한다.
		Context ct ;
		  try {
			ct = new InitialContext();
			 ds = (DataSource)ct.lookup("java:comp/env/jdbc/oracle");
			 
			 Context environmentContext =(Context)ct.lookup("java:/comp/env");
			 int connectionURL = (int) environmentContext.lookup("dbtype");
			  System.out.println(connectionURL);
			  
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    	  		
	}
	
	/*
	 * 2.	컨넥션을 구하는 함수를 제작한다.
	 */
	public Connection getCON() {
		
		
		Connection	 con = null;
		try {
		
			con  = ds.getConnection();
		}
		catch(Exception e) {
			System.out.println("컨넥션 에러 " + e);
		}
		return con;
	}

	/*
	 *	3.	Statement를 만들어주는 함수를 제작한다. 
	 */
	public Statement getSTMT(Connection con) {
		Statement	stmt = null;
		try {
			stmt = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("스테이트먼트 생성 에러 " + e);
		}
		return stmt;
	}
	
	/*
	 * 	4.	PreparedStatement를 만들어주는 함수를 제작한다.
	 */
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement		pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		}
		catch(Exception e) {
			System.out.println("PreparedStatement 생성 에러 " + e);
		}
		return pstmt;
	}

	// 질의 명령을 실행해줄 함수 
	
	public void execute(Statement stmt ,String sql) {
		
		 try {
			  stmt.execute(sql);
		 } catch(Exception e) {}
		 		
	}
	
	
	
	/*
	 * 	5.	닫아주는 함수를 제작한다.
	 */
	public void close(Object obj) {
		try {
			if(obj instanceof Connection) {
				Connection	target = (Connection) obj;
				target.close();
			}
			else if(obj instanceof Statement) {
				Statement	target = (Statement) obj;
				target.close();
			}
			else if(obj instanceof PreparedStatement) {
				((PreparedStatement)obj).close();
			}
			else if(obj instanceof ResultSet) {
				((ResultSet)obj).close();
			}
		}
		catch(Exception e) {
			System.out.println("Close 에러 " + e);
		}
	}
	
}

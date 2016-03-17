package springmyproject.util;

import 	java.sql.*;

import 	javax.naming.Context;
import 	javax.naming.InitialContext;
import 	javax.sql.DataSource;

/*
 * 	컨넥션 풀을 이용해서 컨넥션을 만들어줄 Util 클래스
 */
public class PoolUtil {
	//	생성하는 순간 컨넥션풀를 관리하는 DataSource를 생성한다.
	DataSource		pool;
	public PoolUtil() {
		try {
			Context	ct = new InitialContext();
			pool = (DataSource) ct.lookup("java:comp/env/jdbc/iedu");
		}
		catch(Exception e) {
			System.out.println("컨넥션 풀 획득 에러 = " + e);
		}
	}
	/*
	 * 	필요한 곳에 컨넥션을 나눠줄 함수
	 */
	public Connection getCON() {
		Connection con = null;
		try {
			con = pool.getConnection();
		}
		catch(Exception e) {
			System.out.println("컨넥션 획득 에러 = " + e);
		}
		return con;
	}
	
	/*
	 * 	이제 필요한 기능을 만들어놓자
	 * 	1.	statement를 제공하는 함수들.........
	 */
	public Statement getSTMT(Connection con) {
		Statement	stmt = null;
		try {
			stmt = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("스테이트먼트 생성 에러 = " + e);
		}
		return stmt;
	}
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		}
		catch(Exception e) {
			System.out.println("프리페이드 스테이트먼트 생성 에러 = " + e);
		}
		return pstmt;
	}
	//	질의 명령을 실행해줄 함수
	/*
	public void execute(Statement stmt, String sql) {
		try {
			stmt.execute(sql);
		}
		catch(Exception e) {}
	}
	public void execute(PreparedStatement stmt, Object[] data) {
		try {
			for(int i = 0; i < data.length; i++) {
				stmt.setObject(i + 1, data[i]);
			}
			stmt.execute();
		}
		catch(Exception e) {}
	}

	public ResultSet	executeQuery(Statement stmt, String sql) {
		ResultSet	rs = null;
		try {
			rs = stmt.executeQuery(sql);
		}
		catch(Exception e) {}
		return rs;
	}
	*/
	
	
	/*
	 * 	사용이 끝난 자원을 닫는 함수
	 */
	public void close(Object obj) {
		try {
			if(obj instanceof Connection) {
				((Connection) obj).close();
			}
			else if(obj instanceof Statement) {
				((Statement) obj).close();
			}
			else if(obj instanceof PreparedStatement) {
				((PreparedStatement) obj).close();
			}
			else if(obj instanceof ResultSet) {
				((ResultSet) obj).close();
			}
		}
		catch(Exception e) {}
	}
}








package jdbc;

import java.sql.*;

/*
 * 	이 클래스는 과거에 JDBC 수업을 할 때 만들었던 JDBCUtil 클래스와 같은 역활을 할 클래스이다.
 */
public class WebDB {
	/*
	 * 1.	생성자 함수에서 드라이버를 로딩하도록 한다.
	 */
	public WebDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e) {
			System.out.println("드라이버 로딩 에러 " + e);
		}
	}
	
	/*
	 * 2.	컨넥션을 구하는 함수를 제작한다.
	 */
	public Connection getCON() {
		Connection	con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.81:1521:orcl", "scott", "tiger");
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
package dao;

import 		java.sql.*;
import 		java.util.*;

import 		jdbc.*;

public class LoginDao {
	/*
	 * 	1.	생성하는 순간 데이터베이스에 필요한 내용(드라이버로딩, 컨넥션을 받아온다
	 */
	public Connection	con;
	public	WebDB		db;
	public LoginDao() {
		db = new WebDB();
		con = db.getCON();
	}
	
	/*	 
	 * 	2.	필요한 질의 명령을 실행할 함수를 제작한다.(이 함수는 여러개가 나올 수 있다.)
	 * 
	 * 		1.	로그인 확인 질의 실행
	 */
	public HashMap	isMember(String id, String pw) {
		//	즉 이 함수에게 아이디와 비번을 알려주면 그 회원의 정보를 알려줄 함수를 만든다.
		PreparedStatement		pstmt = null;
		ResultSet					rs = null;
		HashMap					map = new HashMap();
		String	sql = "SELECT * FROM Member WHERE m_ID = ? AND m_Password = ? ";
		try {
			//	그 회원의 정보를 꺼내준다.
			pstmt = db.getPSTMT(con,  sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			//	이 회원의 정보를 HashMap에 넣어서 알려준다.
			//		1.		아이디,		실명,			닉네임
			if(rs.next()) {
				map.put("ID", rs.getString("m_ID"));
				map.put("NAME", rs.getString("m_Name"));
				map.put("NICK", rs.getString("m_Nick"));
			}
		}
		catch(Exception e) {
			System.out.println("로그인 처리 에러 = " + e);
		}
		finally {
			db.close(rs);
			db.close(pstmt);
		}
		return map;
	}
	
	/*
	 * 마지막으로 모든 자원을 닫아주는 함수를 제작한다.
	 */
	public void close() {
		db.close(con);
	}
}




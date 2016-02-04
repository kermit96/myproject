package springmyproject.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import springmyproject.sql.MemberSQL;
import springmyproject.util.PoolUtil;

public class MemberDAO {
	//	생성하면 컨넥션 풀에서 컨넥션을 구한다.
	PoolUtil		pool;
	Connection	con;
	public MemberDAO() {
		pool = new PoolUtil();
		con = pool.getCON();
	}
	/*
	 * 	시도를 구하는 함수
	 */
	public ArrayList getSIDO() {
		String	sql = MemberSQL.getSQL(MemberSQL.GETSIDO);
		Statement stmt = null;
		ResultSet	rs = null;
		ArrayList	list = new ArrayList();
		try {
			stmt = pool.getSTMT(con);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("SIDO"));
			}
		}
		catch(Exception e) {
			System.out.println("시도 검색 에러 = " + e);
		}
		pool.close(rs);
		pool.close(stmt);
		return list;
	}

	/*
	 * 	구군을 검색하는 함수
	 */
	public ArrayList getGugun(String sido) {
		String sql = MemberSQL.getSQL(MemberSQL.GETGUGUN);
		PreparedStatement stmt = null;
		ResultSet	rs = null;
		ArrayList	list = new ArrayList();
		try {
			stmt = pool.getPSTMT(con, sql);
			stmt.setString(1, sido);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("GUGUN"));
			}
		}
		catch(Exception e) {
			System.out.println("구군 검색 에러 = " + e);
		}
		pool.close(rs);
		pool.close(stmt);
		return list;
	}
	/*
	 * 	동 검색 함수
	 */
	public ArrayList getDong(String sido, String gugun) {
		String sql = MemberSQL.getSQL(MemberSQL.GETDONG);
		PreparedStatement stmt = null;
		ResultSet	rs = null;
		ArrayList	list = new ArrayList();
		try {
			stmt = pool.getPSTMT(con, sql);
			stmt.setString(1, sido);
			stmt.setString(2, gugun);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("DONG"));
			}
		}
		catch(Exception e) {
			System.out.println("읍면동 검색 에러 = " + e);
		}
		pool.close(rs);
		pool.close(stmt);
		return list;
	}
	
	/*
	 * 
	 * 
	 */

	public ArrayList getRoad(String sido, String gugun,String dong) {
		String sql = MemberSQL.getSQL(MemberSQL.GETROAD);
		PreparedStatement stmt = null;
		ResultSet	rs = null;
		ArrayList	list = new ArrayList();
		try {
			stmt = pool.getPSTMT(con, sql);
			stmt.setString(1, sido);
			stmt.setString(2, gugun);
			stmt.setString(3, dong);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("road"));
			}
		}
		catch(Exception e) {
			System.out.println("거리 검색 에러 = " + e);
		}
		pool.close(rs);
		pool.close(stmt);
		return list;
	}
	/*
	 * 	우편번호 검색 함수
	 */
	public ArrayList getZipSearch(String sido, String gugun, String dong, String road) {
		//	질의 명령 찾고
		String	sql = MemberSQL.getSQL(MemberSQL.GETZIPCODE);
		PreparedStatement		stmt = null;
		ResultSet					rs = null;
		ArrayList					list = new ArrayList();
		try {
			stmt = pool.getPSTMT(con, sql);
			stmt.setString(1,  sido);
			stmt.setString(2,  gugun);
			stmt.setString(3,  dong);
			stmt.setString(4,  road);
			rs = stmt.executeQuery();
			while(rs.next()) {
				//	한줄의 데이터를 묶는 방법은 우리는 Map으로 하자.
				HashMap		map = new HashMap();
				map.put("ZIPCODE", rs.getString("ZIP"));
				map.put("SIDO", rs.getString("SIDO"));
				map.put("GUGUN", rs.getString("GUGUN"));
				map.put("DONG", rs.getString("DONG"));
				map.put("ROAD", rs.getString("ROAD"));
				map.put("BLG", rs.getString("BLG"));
				map.put("JIBUN", rs.getInt("JIBUN"));
				
				list.add(map);
			}
		}
		catch(Exception e) {
			System.out.println("우편번호 검색 에러 = " + e);
		}
		pool.close(rs);
		pool.close(stmt);
		return list;
	}
	
	
	

	

	//	컨넥션을 반환하는 함수를 제작한다.
	public void close() {
		pool.close(con);
	}
}

package dao;
import		java.sql.*;
import 		java.util.*;
import		jdbc.*;

public class GuestDao {
	public		WebDB		db;
	public		Connection	con;
	/*
	 * 	1.	생성자에서 드라이버 로딩과 컨넥션을 한다.
	 */
	public GuestDao() {
		db = new WebDB();
		con = db.getCON();
	}
	/*
	 * 	2.	두가지 질의 명령을 대신 수행할 함수를 제작한다.
	 */
	//	1.	등록함수
	//		등록을 하기 위해서는 등록할 데이터를 알려주어야 한다.
	public void insertGuest(String writer, String content) {
		PreparedStatement		pstmt = null;
		String	sql = 
				"INSERT INTO Guest VALUES((SELECT NVL(MAX(g_NO), 0) + 1 FROM Guest), ?, ?, SYSDATE)";
		try {
			pstmt = db.getPSTMT(con, sql);
			pstmt.setString(1,  writer);
			pstmt.setString(2,  content);
			pstmt.execute();
		}
		catch(Exception e) {
			System.out.println("방명록 등록 에러 = " + e);
		}
		finally {
			db.close(pstmt);
		}
	}
	
	//	2.	목록 가지고 오는 함수
	//		방명록의 목록은		한줄에 4가지 정보가 들어있고		==>	HashMap로 묶고
	//								그것이 여러줄 있다.					==>	이것을 다시 ArrayList로 묶을 예정이다.
	public ArrayList		getGuest() {
		ArrayList		resultList = new ArrayList();
		Statement	stmt = null;
		ResultSet		rs = null;
		String			sql = "SELECT * FROM Guest ORDER BY g_NO DESC";
		try {
			stmt = db.getSTMT(con);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//	약속대로 한줄의 데이터는 HashMap으로 묶자
				HashMap		map = new HashMap();
				map.put("NO", rs.getInt("g_NO"));
				map.put("WRITER", rs.getString("g_Writer"));
				String	content = rs.getString("g_Content");		//	줄바꿈 기호가 존재하는 내용을 알아낸다.
				content = content.replaceAll("\r\n", "<br>");		//	줄바꿈 기호를 <br> 로 변경한 후
				map.put("CONTENT", content);						//	그 결과를 알려준다.
				//	원래 날짜는 출력할 모습으로 만들어서 제공해야 한다.
				map.put("DATE", rs.getDate("g_Date"));
				//	이 결과를 ArrayList에 넣자
				resultList.add(map);
			}
		}
		catch(Exception e) {
			System.out.println("방명록 목록보기 에러 " + e);
		}
		finally {
			db.close(rs);
			db.close(stmt);
		}
		return resultList;
	}
	
	/*
	 * 	3.	자원을 닫는다.
	 */
	public void close() {
		db.close(con);
	}
}





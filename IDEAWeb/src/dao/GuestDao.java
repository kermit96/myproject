package dao;
import		java.sql.*;
import 		java.util.*;
import		jdbc.*;

public class GuestDao {
	public		WebDB		db;
	public		Connection	con;
	/*
	 * 	1.	�����ڿ��� ����̹� �ε��� ���ؼ��� �Ѵ�.
	 */
	public GuestDao() {
		db = new WebDB();
		con = db.getCON();
	}
	/*
	 * 	2.	�ΰ��� ���� ����� ��� ������ �Լ��� �����Ѵ�.
	 */
	//	1.	����Լ�
	//		����� �ϱ� ���ؼ��� ����� �����͸� �˷��־�� �Ѵ�.
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
			System.out.println("���� ��� ���� = " + e);
		}
		finally {
			db.close(pstmt);
		}
	}
	
	//	2.	��� ������ ���� �Լ�
	//		������ �����		���ٿ� 4���� ������ ����ְ�		==>	HashMap�� ����
	//								�װ��� ������ �ִ�.					==>	�̰��� �ٽ� ArrayList�� ���� �����̴�.
	public ArrayList		getGuest() {
		ArrayList		resultList = new ArrayList();
		Statement	stmt = null;
		ResultSet		rs = null;
		String			sql = "SELECT * FROM Guest ORDER BY g_NO DESC";
		try {
			stmt = db.getSTMT(con);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//	��Ӵ�� ������ �����ʹ� HashMap���� ����
				HashMap		map = new HashMap();
				map.put("NO", rs.getInt("g_NO"));
				map.put("WRITER", rs.getString("g_Writer"));
				String	content = rs.getString("g_Content");		//	�ٹٲ� ��ȣ�� �����ϴ� ������ �˾Ƴ���.
				content = content.replaceAll("\r\n", "<br>");		//	�ٹٲ� ��ȣ�� <br> �� ������ ��
				map.put("CONTENT", content);						//	�� ����� �˷��ش�.
				//	���� ��¥�� ����� ������� ���� �����ؾ� �Ѵ�.
				map.put("DATE", rs.getDate("g_Date"));
				//	�� ����� ArrayList�� ����
				resultList.add(map);
			}
		}
		catch(Exception e) {
			System.out.println("���� ��Ϻ��� ���� " + e);
		}
		finally {
			db.close(rs);
			db.close(stmt);
		}
		return resultList;
	}
	
	/*
	 * 	3.	�ڿ��� �ݴ´�.
	 */
	public void close() {
		db.close(con);
	}
}





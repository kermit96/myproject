package dao;

import java.sql.*;
import java.util.ArrayList;

import iedu.data.FileBoardData;
import iedu.util.PageInfo;
import jdbc.PoolDB;

/*
 * 	컨넥션 풀을 이용한 데이터베이스 작업을 전담할 클래스
 */
public class FileBoardDAO {
	//	생성하는 순간 컨넥션 풀에 있는 컨넥션을 받아오도록 하자.
	public	PoolDB			db;
	public Connection	con;
	public FileBoardDAO() {
		db = new PoolDB();
		con = db.getCON();
	}
	//	이제부터는 필요한 질의 명령을 수행할 함수를 만들어나가면 된다.
	//	1.	데이터베이스에 데이터를 기록해 주는 함수
	public void insertBoard(FileBoardData data) {
		//	질의 명령 알아내고
		String	sql = FileBoardSQL.getSQL(FileBoardSQL.INSERTBOARD);

		PreparedStatement	pstmt = null;
		try {
			//	스테이트먼트 만들어서
			pstmt = db.getPSTMT(con, sql);
			//	?를 채우고
			pstmt.setString(1, data.title);
			pstmt.setString(2, data.body);
			pstmt.setString(3, data.writer);
			pstmt.setString(4, data.path);
			pstmt.setString(5, data.oriName);
			pstmt.setString(6, data.saveName);
			pstmt.setLong(7, data.len);
			//	실행한다.
			pstmt.execute();
		}
		catch(Exception e) {
			System.out.println("기록 에러 = " + e);
		}
		db.close(pstmt);
	}
	/*
	 * 	총 데이터 개수를 알려주는 함수
	 */
	public int getTotal() {
		String sql = FileBoardSQL.getSQL(FileBoardSQL.GETTOTAL);
		Statement		stmt = null;
		ResultSet		rs = null;
		int				total = 0;		//	결과를 기억할 변수
		try {
			stmt = db.getSTMT(con);
			rs = stmt.executeQuery(sql);
			rs.next();
			total = rs.getInt("TOTAL");
		}
		catch(Exception e) {
			System.out.println("총 데이터 개수 구하기 에러 " + e);
		}
		db.close(rs);
		db.close(stmt);
		return total;
	}
	
	/*
	 * 	게시물 목록 꺼내기 함수
	 */
	public ArrayList getBoardList(PageInfo pInfo) {
		String	sql = FileBoardSQL.getSQL(FileBoardSQL.GETBOARDLIST);
		Statement		stmt = null;
		ResultSet		rs = null;
		ArrayList		list = new ArrayList();
		try {
			stmt = db.getSTMT(con);
			rs = stmt.executeQuery(sql);
			//	현재 페이지 이전에 보여줄 목록은 스킵 시킨다.
			for(int i = 0; i < (pInfo.nowPage - 1) * pInfo.pageList; i++) {
				rs.next();
			}
			//	현재 페이지에서 보여줄 개수만 꺼낸다.
			for(int i = 0; i < pInfo.pageList && rs.next(); i++) {
				//	꺼낸 결과는 데이터빈 클래스로 묶어서
				FileBoardData		data = new FileBoardData();
				data.no 		= rs.getInt("NO");
				data.title 		= rs.getString("TITLE");
				data.writer 	= rs.getString("WRITER");
				data.len 		= rs.getLong("LEN");
				//	이것을 다시 컬렉션에 저장한 후 알려주도록 한다.
				list.add(data);
			}
		}
		catch(Exception e) {
			System.out.println("목록 꺼내기 에러 = " + e);
		}
		db.close(rs);
		db.close(stmt);
		return list;
	}
	
	public FileBoardData GetView(int no)
	{
		String	sql = FileBoardSQL.getSQL(FileBoardSQL.GETBOARDVIEW);
				
		PreparedStatement ptmt = null;		
		ResultSet		rs = null;
		FileBoardData  data = new FileBoardData(); 
		
		
		try {
			ptmt = db.getPSTMT(con, sql);
			rs = ptmt.executeQuery();
			while(rs.next())
			{
				
				
			}
		}
		catch(Exception e) {
			System.out.println("File View data " + e);
		}
		db.close(rs);
		db.close(ptmt);
		
		return data;
	}
	
	/*
	 * 	검색 데이터 개수를 구하는 함수
	 */
	public int getSearchTotal(String kind, String content) {
		String	sql = FileBoardSQL.getSQL(FileBoardSQL.GETSEARCHTOTAL);
		String temp = "";
		if(kind.equals("all")) {
			temp = "fb_Title LIKE '%" + content + "%' OR fb_Body LIKE '%"+ content + "%'";
		}
		else {
			temp = kind + " LIKE '%" + content + "%'";
			//	예		글쓴이 검색		isundol
			//		temp = 			fb_Writer LIKE '%isundol%'
		}
		sql = sql.replaceAll("%%%", temp);
		
		
		Statement		stmt = null;
		ResultSet		rs = null;
		int				total = 0;		//	결과를 기억할 변수
		try {
			stmt = db.getSTMT(con);
			rs = stmt.executeQuery(sql);
			rs.next();
			total = rs.getInt("TOTAL");
		}
		catch(Exception e) {
			System.out.println("검색 데이터 개수 구하기 에러 " + e);
		}
		db.close(rs);
		db.close(stmt);
		return total;
	}

	/*
	 * 	검색 목록 구하기 함수
	 */
	public ArrayList	getSearchList(PageInfo pInfo, String kind, String content) {
		String	sql = FileBoardSQL.getSQL(FileBoardSQL.GETBOARDSEARCHLIST);
		String temp = "";
		if(kind.equals("all")) {
			temp = "fb_Title LIKE '%" + content + "%' OR fb_Body LIKE '%"+ content + "%'";
		}
		else {
			temp = kind + " LIKE '%" + content + "%'";
		}
		sql = sql.replaceAll("%%%", temp);
		Statement		stmt = null;
		ResultSet		rs = null;
		ArrayList		list = new ArrayList();
		try {
			stmt = db.getSTMT(con);
			rs = stmt.executeQuery(sql);
			//	현재 페이지 이전에 보여줄 목록은 스킵 시킨다.
			for(int i = 0; i < (pInfo.nowPage - 1) * pInfo.pageList; i++) {
				rs.next();
			}
			//	현재 페이지에서 보여줄 개수만 꺼낸다.
			for(int i = 0; i < pInfo.pageList && rs.next(); i++) {
				//	꺼낸 결과는 데이터빈 클래스로 묶어서
				FileBoardData		data = new FileBoardData();
				data.no 		= rs.getInt("NO");
				data.title 		= rs.getString("TITLE");
				data.writer 	= rs.getString("WRITER");
				data.len 		= rs.getLong("LEN");
				//	이것을 다시 컬렉션에 저장한 후 알려주도록 한다.
				list.add(data);
			}
		}
		catch(Exception e) {
			System.out.println("검색 목록 꺼내기 에러 = " + e);
		}
		db.close(rs);
		db.close(stmt);
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	//	마지막으로 사용이 끝난 컨넥션을 반환하는 함수를 만들자
	public void closeCON() {
		db.close(con);
	}
}

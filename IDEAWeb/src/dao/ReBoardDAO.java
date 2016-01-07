package dao;

import 	jdbc.WebDB;
import		java.sql.*;
import java.util.ArrayList;

import iedu.data.ReBoardData;
import iedu.util.PageInfo;

/*
 * 	답변형 게시판에서 데이터베이스 질의 처리를 전담해줄 클래스
 */
public class ReBoardDAO {
	//	데이터베이스 접속
	//	생성을 하는 순간 데이터베이스 접속을 하도록 할 예정이다.
	WebDB					db;
	Connection			con;
	Statement				stmt;
	PreparedStatement	pstmt;
	ResultSet				rs;
	public ReBoardDAO() {
		db = new WebDB();
		con = db.getCON();
	}
	//	이 부분에 이제부터 모델에서 필요한 데이터베이스 작업이 있으면
	//	함수를 제작해서 모델에서는 데이터베이스를 건딜지 말고
	//	모든 데이터베이스 작업은 이곳에서 모아서 하도록 분산해 놓는다.
	//	분산 처리의 장점
	//	1.	일을 분산해서 처리할 수 있다.
	//	2.	유지 보수가 편하다.
	public void insertBoard(ReBoardData data) {
		//	질의 명령을 실행하기 위해서 필요한 데이터는 데이터 빈 클래스로 오기로
		//	약속했다.
		//	할일
		//		1.	일련번호를 알아낸다.
		stmt = db.getSTMT(con);
		String	sql = ReBoardSQL.getSQL(ReBoardSQL.GETMAXNO);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			int	maxno = rs.getInt("MAXNO");
			db.close(rs);
			//		2.	게시물을 입력한다.
			sql = ReBoardSQL.getSQL(ReBoardSQL.INSERTBOARD);
			pstmt = db.getPSTMT(con, sql);
			pstmt.setInt(1, maxno);
			pstmt.setString(2, data.title);
			pstmt.setString(3, data.writer);
			pstmt.setString(4, data.body);
			pstmt.setInt(5, maxno);
			pstmt.setInt(6,  data.step);
			pstmt.setInt(7,  data.order);
			pstmt.execute();
		}
		catch(Exception e) {
			System.out.println("인써트 에러 = " + e);
		}
		close(pstmt);
		close(stmt);
		close(con);
	}

	/*
	 * 페이지 정보를 만들어주는 함수
	 */
	public PageInfo getPageInfo(int nowPage) {
		//	할일
		//		총 데이터의 갯수를 알아낸다.
		stmt = db.getSTMT(con);
		String	sql = ReBoardSQL.getSQL(ReBoardSQL.GETTOTALCOUNT);
		PageInfo	pInfo = null;
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			int	totalCount = rs.getInt("TOTALC");
			rs.close();
			//		그것을 바탕으로 페이지 정보를 알아낸다.
			pInfo = new PageInfo(nowPage, totalCount, 5, 5);
			pInfo.calcInfo();
		}
		catch(Exception e) {
			System.out.println("총 데이터 개수 구하기 에러 = " + e);
		}
		close(stmt);
//		close(con);
		return pInfo;
	}
	
	/*
	 * 게시물의 목록을 구해주는 함수
	 */
	public ArrayList getBoardList(PageInfo pInfo) {
		//	할일
		//		목록을 구한후
		stmt = db.getSTMT(con);
		String sql = ReBoardSQL.getSQL(ReBoardSQL.GETBOARDLIST);
		ArrayList	list = new ArrayList();		//최종 결과 기억할 변수
		try {
			rs = stmt.executeQuery(sql);
			//		그 페이지서 보여줄 내용만 꺼낸 후 알려준다.
			//	이전페이지에서 보여줄 내용은 넘겨버리자.
			for(int i = 0; i < (pInfo.nowPage - 1) * pInfo.pageList; i++) {
				rs.next();
			}
			//	지금 페이지에서 보여줄 내용만 꺼내서 보관하자.
			for(int i = 0; i < pInfo.pageList && rs.next(); i++) {
				//	마지막 페이지는 5개가 없을 수 있으므로....
				//	이제 꺼낸 데이터를 어떻게 알려줄 것인가?
				//	한줄의 데이터는 데이터 빈 클래스에 묶고
				ReBoardData	data = new ReBoardData();
				data.no 		= rs.getInt("NO");
				data.title 		= rs.getString("TITLE");
				data.writer 	= rs.getString("WRITER");
				data.date 		= rs.getDate("WDATE");
				data.hit 		= rs.getInt("HIT");
				data.group 	= rs.getInt("BGROUP");
				data.step 		= rs.getInt("BSTEP");
				data.order 	= rs.getInt("BORDER");
				//	이것을 다시 ArrayList에 묶어서 알려주도록 하자.
				list.add(data);
			}
			close(rs);
		}
		catch(Exception e) {
			System.out.println("목록 구하기 에러 = " + e);
		}
		close(stmt);
		close(con);
		return list;
	}

	/*
	 * 	상세정보를 알려주는 데이터베이스 함수
	 */
	public ReBoardData getBoardView(int oriNO) {
		//	이 함수가 하는 역활은 특정 게시물 하나를 꺼내서 모델에게
		//	알려주는 기능이다.

		//	질의 명령 알아낸다.
		String	sql = ReBoardSQL.getSQL(ReBoardSQL.GETBOARDVIEW);
		ReBoardData	data = new ReBoardData();
		try {
			pstmt = db.getPSTMT(con, sql);
			pstmt.setInt(1, oriNO);
			rs = pstmt.executeQuery();
			rs.next();
			//	필요한 데이터를 데이터빈 클래스에 묶는다.
			data.no = rs.getInt("NO");
			data.title = rs.getString("TITLE");
			data.writer = rs.getString("WRITER");
			data.body = rs.getString("BODY");
			data.date = rs.getDate("WDATE");
			data.hit = rs.getInt("HIT");
			data.ok = rs.getInt("OK");
			data.bed = rs.getInt("BED");
			data.group = rs.getInt("BGROUP");
			data.step = rs.getInt("BSTEP");
			data.order = rs.getInt("BORDER");
		}
		catch(Exception e) {
			System.out.println("상세보기 에러 = " + e);
		}
		db.close(rs);
		db.close(pstmt);
		return data;
	}

	public void updateHit(int oriNO) {
		//	할일
		//		조회수를 증가하는 일
		String	sql = ReBoardSQL.getSQL(ReBoardSQL.UPDATEHIT);
		try {
			pstmt = db.getPSTMT(con, sql);
			pstmt.setInt(1, oriNO);
			pstmt.execute();
		}
		catch(Exception e) {
			System.out.println("조회수 증가 에러 = " + e);
		}
	}

	public void ReBoardWrite(ReBoardData data) {
		//	할일
		//		게시판에 글쓰기를 등록해준다.
		
		//	원본글의 정보를 꺼낸다.
		//		이미 상세보기에서 특정글에 대한 정보를 구하는 기능을 만들어
		//		놓았으므로 그것을 이용하도록 한다.
		ReBoardData	temp = getBoardView(data.no);
		
		//	이 정보를 이용해서 현재 글에 대한 group, step, order를 결정한다.
		data.group = temp.group;
		data.step = temp.step + 1;
		data.order = temp.order + 1;
		
		//	글쓰기를 한다.
		try {
			//	1.	order를 먼저 세팅한다.
			String sql = ReBoardSQL.getSQL(ReBoardSQL.UPDATEORDER);
			pstmt = db.getPSTMT(con, sql);
			pstmt.setInt(1, data.group);
			pstmt.setInt(2, data.order);
			pstmt.execute();
			pstmt.close();
			//	데이터베이스에 기록한다.
			stmt = db.getSTMT(con);
			//		1.	현재글의 글번호를 알아야 한다.
			sql = ReBoardSQL.getSQL(ReBoardSQL.GETMAXNO);
			rs = stmt.executeQuery(sql);
			rs.next();
			int	nowNO = rs.getInt("MAXNO");
			rs.close();
			//	2.	데이터베이스에 기록한다.
			sql = ReBoardSQL.getSQL(ReBoardSQL.INSERTBOARD);
			pstmt = db.getPSTMT(con, sql);
			pstmt.setInt(1, nowNO);
			pstmt.setString(2, data.title);
			pstmt.setString(3, data.writer);
			pstmt.setString(4, data.body);
			pstmt.setInt(5,  data.group);
			pstmt.setInt(6,  data.step);
			pstmt.setInt(7,  data.order);
			pstmt.execute();
		}
		catch(Exception e) {
			System.out.println("답글쓰기 에러 = " + e);
		}
		db.close(pstmt);
		db.close(stmt);
	}
	
	public void updateGood(int oriNO) {
		//	좋아요 숫자를 증가시킨다.
		String	sql = ReBoardSQL.getSQL(ReBoardSQL.UPDATEGOOD);
		try {
			pstmt = db.getPSTMT(con, sql);
			pstmt.setInt(1, oriNO);
			pstmt.execute();
		}
		catch(Exception e) {
			
		}
		db.close(pstmt);
	}
	
	
	//	닫기를 전담할 함수
	public void close(Object obj) {
		db.close(obj);
	}
	//	dao에서 작업을 종료한 후 컨넥션을 닫다보니까 문제가 생겼다.
	//	하나의 모델에서 두가지 이상 데이터베이스 작업을 할 때 문제가 생긴다.
	//		한번 작업을 할 때 마다 다시 데이터베이스에 연결해야 하는 문제가 생겼다.
	//	컨넥션은 모델에서 닫을 수 있도록 하자.
	
	//	컨넥션을 닫는 함수
	public void closeCon() {
		db.close(con);
	}
}



package springmyproject.dao;

import java.sql.*;
import java.util.ArrayList;

import springmyproject.data.NoticeData;
import springmyproject.sql.NoticeSql;
import springmyproject.util.PoolUtil;

/*
 * 	이 클래스는 공지사항 데이터베이스 처리를 담당
 */
public class NoticeDao {
	/*
	 * 	이 클래스를 new 시키면 컨넥션 풀에서 컨넥션을 받아주도록 한다.
	 */
	PoolUtil			pool;
	Connection		con;
	public NoticeDao() {
		pool = new PoolUtil();
		con = pool.getCON();
	}
	/*
	 * 	공지사항 쓰기 함수
	 */
	public void noticeWrite(NoticeData data) {
		//	질의 명령 알아내고
		String	sql = NoticeSql.getSQL(NoticeSql.INSERTNOTICE);
		PreparedStatement stmt = null;
		try {
			//	스테이트먼트 알아내서
			stmt = pool.getPSTMT(con, sql);
			//	? 채우고
			stmt.setString(1, data.kind);
			stmt.setString(2, data.writer);
			stmt.setString(3, data.title);
			stmt.setString(4, data.body);
			//	insert 시킨다.
			stmt.execute();
		}
		catch(Exception e) {
			System.out.println("공지사항 등록 에러 = " + e);
		}
		pool.close(stmt);
	}
	/*
	 * 	총 데이터개수 구하기 함수
	 */
	public int getTotal() {
		String	sql = NoticeSql.getSQL(NoticeSql.GETTOTAL);
		Statement		stmt = null;
		ResultSet		rs = null;
		int				total = 0;			//	반환할 결과를 기억할 변수
		try {
			stmt = pool.getSTMT(con);
			rs = stmt.executeQuery(sql);
			rs.next();
			total = rs.getInt("TOT");
		}
		catch(Exception e) {
			System.out.println("총 데이터 갯수 구하기 에러 = " + e);
		}
		pool.close(rs);
		pool.close(stmt);
		return total;
	}
	/*
	 * 	공지사항 목록 구하기 함수
	 */
	public ArrayList getList(int start, int end) {
		//	매개변수의 역활
		//	==>		구하고자 하는 게시물의 시작번호, 끝번호를 알려준다.
		String sql = NoticeSql.getSQL(NoticeSql.GETNOTICELIST);
		PreparedStatement		stmt = null;
		ResultSet					rs = null;
		ArrayList<NoticeData>	list = new ArrayList<NoticeData>();
		try {
			stmt = pool.getPSTMT(con, sql);
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			//	그러면 rs에는 이번에 보여줄 데이터만 딱 구했다.
			//	전체를 다 알려주면 된다.
			while(rs.next()) {
				//	한줄씩의 데이터는 데이터빈으로 묶어서 이것을 다시 ArrayList로 
				//	묶어서 알려주자.
				NoticeData	data = new NoticeData();
				data.rno = rs.getInt("RNO");
				data.no = rs.getInt("NO");
				data.kind = rs.getString("KIND");
				data.title = rs.getString("TITLE");
				data.writer = rs.getString("WRITER");
				data.body = rs.getString("BODY");
				//	날짜를 꺼낼때....
				//		날짜가 필요하면 	rs.getDate();
				//		시간이 필요하면 	rs.getTime();		으로 꺼내야 한다.
				data.date = rs.getDate("WDATE");
				data.time = rs.getTime("WDATE");
				
				list.add(data);
			}
		}
		catch(Exception e) {
			System.out.println("공지사항 목록 꺼내기 에러 = " + e);
		}
		pool.close(rs);
		pool.close(stmt);
		return list;
	}
	
	
	/*
	 * 	이 DAO가 사용한 connection을 반환하는 함수
	 */
	public void close() {
		pool.close(con);
	}
}






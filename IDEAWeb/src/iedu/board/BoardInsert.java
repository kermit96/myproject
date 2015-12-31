package iedu.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.WebDB;

public class BoardInsert implements BoardMain {
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		넘어온 파라메터를 받아낸다.
		String		title = req.getParameter("title");
		String		body = req.getParameter("body");
		//	아시다시피 글쓴이는 disabled 되어 있으므로 넘어오지 못한다.
		//	그래서 강제로 세션에서 알아내야 한다.
		//	문제		JSP는 session이 존재하지만 모델은 session이 없다.
		//	해결		session을 만들어서 사용해야 한다.
		HttpSession	session = req.getSession();
		String		id = (String) session.getAttribute("ID");
		
		//		이 내용을 데이터베이스에 기록한다.
		//		원래 데이터베이스 처리는 dao 클래스를 만들어서 사용하는 것이 실무에서의 원칙이다.
		//		이번 게시판은 막코딩으로 하겠다.
		WebDB					db = null;
		Connection				con = null;
		PreparedStatement		pstmt = null;
		try {
			db = new WebDB();
			con = db.getCON();
			String	sql = "INSERT INTO ReBoard VALUES((SELECT NVL(MAX(rb_NO), 0) + 1 FROM ReBoard), ?, ?, ?, SYSDATE, 0, 'Y')";
			pstmt = db.getPSTMT(con, sql);
			pstmt.setString(1, title);
			pstmt.setString(2, id);
			pstmt.setString(3, body);
			pstmt.execute();
		}
		catch(Exception e) {
			System.out.println("게시물 등록 에러 = " + e);
		}
		finally {
			db.close(pstmt);
			db.close(con);
		}
		//		결과물은 없으므로...	뷰를 선택한다.
		return "/Board/BoardInsert.jsp";
	}

}

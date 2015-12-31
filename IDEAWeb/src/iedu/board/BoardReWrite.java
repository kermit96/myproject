package iedu.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.WebDB;

/*
 * 	모델은 다형성 구현을 하기 위해서 메인 모델을 상속받아서 만들기로 약속했다.
 */
public class BoardReWrite implements BoardMain {
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		넘어온 파라메터 받는다.
		String		strOriNO = req.getParameter("oriNO");
		int			oriNO = Integer.parseInt(strOriNO);
		String		body = req.getParameter("body");
		HttpSession	session = req.getSession();
		String		writer = (String) session.getAttribute("ID");
		
		//		데이터베이스에 기록한다.
		WebDB				db = null;
		Connection			con = null;
		PreparedStatement	pstmt = null;
		try {
			db = new WebDB();
			con = db.getCON();
			String	sql = "INSERT INTO ReReply VALUES((SELECT NVL(MAX(rr_NO), 0) + 1 FROM ReReply), ?, ?, ?, SYSDATE, 'Y')";
			pstmt = db.getPSTMT(con, sql);
			pstmt.setInt(1, oriNO);
			pstmt.setString(2,  writer);
			pstmt.setString(3, body);
			pstmt.execute();
		}
		catch(Exception e) {
			System.out.println("댓글 등록 에러 = " + e);
		}
		finally {
			db.close(pstmt);
			db.close(con);
		}
		//		뷰에서 사용할 데이터를 반드시 보내주어야 한다.
		req.setAttribute("ORINO", oriNO);
		//		뷰를 선택한다.
		return "/Board/BoardReWrite.jsp";
	}
}






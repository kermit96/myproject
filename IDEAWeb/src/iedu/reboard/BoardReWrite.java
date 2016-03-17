package iedu.reboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReBoardDAO;
import iedu.data.ReBoardData;

public class BoardReWrite implements ReBoardMain {
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		넘어온 파라메터를 받는다.
		String	strNO = req.getParameter("oriNO");
		int		oriNO = Integer.parseInt(strNO);
		String	title = req.getParameter("title");
		String	body = req.getParameter("body");
		
		//	글쓴이는 세션에서 알아내야 한다.
		HttpSession	session = req.getSession();
		String	id = (String) session.getAttribute("ID");
		
		//	이제 이 정보를 데이터베이스에게 기억하라고 DAO에게 부탁할 예정이다.
		//	넘어온 데이터가 많으므로 이것을 데이터빈으로 묶어서
		//	DAO에게 알려준다.
		ReBoardData	data = new ReBoardData();
		data.title = title;
		data.body = body;
		data.no = oriNO;
		data.writer = id;
		
		ReBoardDAO	dao = new ReBoardDAO();
		dao.ReBoardWrite(data);
		dao.closeCon();
		
		return "../ReBoard/BoardReWrite.jsp";
	}
}




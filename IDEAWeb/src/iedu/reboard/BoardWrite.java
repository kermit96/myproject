package iedu.reboard;

import 	javax.servlet.http.HttpServletRequest;
import 	javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReBoardDAO;
import iedu.data.ReBoardData;

public class BoardWrite implements ReBoardMain {
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		1.	세션이 없으면......
		HttpSession	session = req.getSession();
		String	id = (String) session.getAttribute("ID");
		if(id == null || id.length() == 0) {
			return "../Member/LoginForm.jsp";
		}
		//		2.	넘어온 데이터 받는다.
		String	title = req.getParameter("title");
		String	body = req.getParameter("body");
		//	여기서도 마찬가지로 주소를 치고 들어온 경우는 파라메터를 주지 못할 수 있다.
		if(title == null || title.length() == 0) {
			return "../Member/LoginForm.jsp";
		}
		if(body == null || body.length() == 0) {
			return "../Member/LoginForm.jsp";
		}
		//	이제 데이터베이스에 INSERT를 시켜야한다.
		//	데이터베이스에 INSERT 시키기 위해서는 필요한 데이터를 만들어서
		//	우리는 DAO쪽에 알려주면 DAO에서 처리하기로 약속했다.
		//	데이터는	데이터빈 클래스에 묶어서 알려주기로 한다.
		ReBoardData	data = new ReBoardData();
		data.title = title;
		data.body = body;
		data.writer = (String)session.getAttribute("ID");
		data.group = 0;
		data.step = 0;
		data.order = 0;
		//	DAO 클래스에게 INSERT 작업을 하도록 지시한다.
		ReBoardDAO	dao = new ReBoardDAO();
		dao.insertBoard(data);
		return "../ReBoard/BoardWrite.jsp";
	}
}
/*
 * 	모델에서는 데이터베이스를 건딜지 않도록 약속하고
 * 	데이터베이스 작업이 필요하면  DAO 클래스한테 부탁하도록 약속한다.
 * 	대신 데이터베이스 작업에 필요한 데이터는 모델이 만들어서 넘겨주기로 한다.
 * 
 */



















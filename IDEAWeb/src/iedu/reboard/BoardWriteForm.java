package iedu.reboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 *	다형성 구현을 위해서 모든 모델은 그 모델의 상위클래스(인터페이스)를
 *	상속받아서 만든다.
 */
public class BoardWriteForm implements ReBoardMain {
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		다른 할일은 없는데...	혹시 주소를 직접 입력해서 들어오는 경우를 대비해서
		//		세션을 확인하고 세션이 존재하지 않으면 로그인 폼으로 보내주어야 한다.
		//	항상 특정한 조건에 만족하는 경우에만 실행할 문서들은
		//	반드시 그 조건이 맞는지 꼭 확인한 후 다음 작업을 해주어야 한다.
		//	특히 관리자 모드를 만드는 경우에는 좀더 신경을 써서 만들어 주어야 한다.
		HttpSession	session = req.getSession();
		String	id = (String) session.getAttribute("ID");
		if(id == null || id.length() == 0) {
			return "../Member/LoginForm.jsp";
		}
		return "../ReBoard/BoardWriteForm.jsp";
	}
}







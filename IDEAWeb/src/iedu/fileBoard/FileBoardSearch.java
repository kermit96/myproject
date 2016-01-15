package iedu.fileBoard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FileBoardDAO;
import iedu.util.PageInfo;

public class FileBoardSearch extends FileBoardMain {
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		넘어온 파라메터 받기
		String		kind = req.getParameter("kind");
		String		content = req.getParameter("content");
		String		strPage = req.getParameter("nowPage");
		int			nowPage = 0;
		if(strPage == null || strPage.length() == 0) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	문제
		//		검색을 처음 할 경우에는 폼에서 kind, content가 넘어온다.
		//		페이지 나눔 기능을 이용할 경우에는 넘어오지 않을 수 있다.
		//	해결방법
		//		1.	릴레이식으로 해서 계속해서 kind, content를 넘긴다.
		//		2.	세션을 이용하는 방법
		//			즉, 세션에 현재 검색하는 kind, content를 기억해 놓았다가 사용하는 방법
		HttpSession	session = req.getSession();
		if(kind == null || kind.length() == 0) {
			//	kind가 넘어오지 않았으면 당연히 content도 없을 것이다.
			//	이 경우에는 세션에서 그 내용을 꺼낸다.
			kind = (String)session.getAttribute("KIND");
			content = (String) session.getAttribute("CONTENT");
		}
		//	★
		//	드디어 검색할 준비가 되었다.
		//	다음에 검색을 할 경우를 대비해서 이 정보를 세션이 반드시 기록해 놓는다.
		session.setAttribute("KIND", kind);
		session.setAttribute("CONTENT", content);
		//	그래야 다음에 다시 시도할 때 받지 못하면 세션에서 꺼낼 수 있다.
		
		//	데이터베이스에게 부탁한다.
		//	1.	총 데이터 갯수
		FileBoardDAO		dao = new FileBoardDAO();
		int	total = dao.getSearchTotal(kind, content);
		PageInfo 	pInfo = new PageInfo(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	2.	목록보기를 위한 검색
		ArrayList	list = dao.getSearchList(pInfo, kind, content);
		dao.closeCON();
		
		req.setAttribute("PINFO", pInfo);
		req.setAttribute("LIST", list);
		
		return "/FileBoard/FileBoardSearch.jsp";
	}
}

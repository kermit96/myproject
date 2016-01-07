package iedu.reboard;

import java.util.ArrayList;

import 	javax.servlet.http.HttpServletRequest;
import 	javax.servlet.http.HttpServletResponse;

import dao.ReBoardDAO;
import iedu.util.PageInfo;

/*
 * 	목록보기 요청을 처리할 모델 클래스
 */
public class BoardList implements ReBoardMain {
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//	목록보기 요청을 처리할 내용을 코딩해서.........
		//	아직 게시물이 없으므로 결과를 보지 못하기 때문에 이 부분은 다음에 처리하고
		//	할일
		//		넘어온 파라메터를 받는다.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(strPage == null || strPage.length() == 0) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//		페이지 정보를 알아낸다.
		//		페이지 정보를 알기 위해서는 총 데이터 개수를 알아야 한다.
		//		이것은 데이터베이스에 관련된 작업이므로 DAO에게 맡기도록 한다.
		ReBoardDAO	dao = new ReBoardDAO();
		PageInfo 		pInfo = dao.getPageInfo(nowPage);
		//		목록을 꺼낸다.
		ArrayList		list = dao.getBoardList(pInfo);
		
		//		뷰에게 알려준다.
		req.setAttribute("PINFO", pInfo);
		req.setAttribute("LIST", list);
		
		//	뷰에게 넘겨줄 데이터 만들고......
		//	뷰를 선택한다.
		return "/ReBoard/BoardList.jsp";
		//	왜 뷰를 선택하도록 해 놓았나?
		//	이유
		//	모델을 실행하다보면 뷰가 달라지는 경우가 생길수 있다.
		//	예>		로그인을 하지 않은 사람이라고 판정나면
		//			뷰는		로그인 처리로 가야한다.
	}
}





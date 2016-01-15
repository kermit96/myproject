package iedu.fileBoard;

import java.util.ArrayList;

import 	javax.servlet.http.HttpServletRequest;
import 	javax.servlet.http.HttpServletResponse;

import dao.FileBoardDAO;
import iedu.util.PageInfo;

public class FileBoardList extends FileBoardMain {
	public String service(HttpServletRequest req, HttpServletResponse resp){
		//	할일
		//		보고싶은 페이지를 알아낸다.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(strPage == null || strPage.length() == 0) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		//	1.	페이지 정보를 만든다.
		//		(총 데이터의 갯수를 알아야 한다.)
		FileBoardDAO	dao = new FileBoardDAO();
		
		int	total = dao.getTotal();
		
		PageInfo	pInfo = new PageInfo(nowPage, total, 5, 5);
		pInfo.calcInfo();
		
		//	2.	목록을 꺼낸다.
		//		dao에게 현재 페이지 정보를 알려주면서
		//		그 페이지에서 보여줄 정보만 꺼내 달라고 부탁한다.
		ArrayList	list = dao.getBoardList(pInfo);
		dao.closeCON();
		
		//	3.	뷰에게 알려준다.
		req.setAttribute("PINFO", pInfo);
		req.setAttribute("LIST", list);
		
		
		//	뷰만 불러주자.
		return "/FileBoard/FileBoardList.jsp";
	}
}




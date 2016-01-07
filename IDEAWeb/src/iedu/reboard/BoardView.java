package iedu.reboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReBoardDAO;
import iedu.data.ReBoardData;

public class BoardView implements ReBoardMain {
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//	1.	넘어온 번호를 받는다
		String	strNO = req.getParameter("oriNO");
		int		oriNO = Integer.parseInt(strNO);
		//	이 부분은 파라메터 릴레이를 해주기 위해서 받은 것이다.
		//	곧장 뷰에게만 알려주면 된다.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = Integer.parseInt(strPage);
		
		//	조회수 증가가 무한대로 되면 안되므로.... 조절을 해야할 것이다.
		//	1.	자신의 글은 조회수를 증가하면 안되도록 하자.
		boolean		isHit = true;		//	증가하는 것으로 인정하고.....
		ReBoardDAO	dao = new ReBoardDAO();

		//		1)	현재 상태대로 게시물을 꺼낸다.(글쓴이를 알기 위해서)
		ReBoardData	temp = dao.getBoardView(oriNO);
		HttpSession	session = req.getSession();
		String	id = (String) session.getAttribute("ID");
		if(temp.writer.equals(id)) {
			isHit = false;
		}
		
		//		2.	한번 보았던글은 조회수를 증가하면 안되도록 하자.
		if(isHit == true) {
			//	본 글을 검사하는 방법은
			//		1.	세션을 이용하는 방법
			//		원리
			//			세션에 현재 보고있는 글이 있는지 확인한다.
			//			약속		session.setAttribute("HIT", 본글);
			//			있으면 증가하면 안되고
			//			없으면 증가한다.
			//				대신에 본 글은 다시 세션에 기억해 놓아서 다음에는 증가못하도록 한다.
			String	oldHit = (String) session.getAttribute("HIT");
			if(oldHit == null || oldHit.length() == 0) {
				//	아직까지 한번도 본 적이 없다.
				isHit = true;
				//	대신에 지금 본 글은 다시는 조회수를 증가하면 안되므로 세션에 등록해 놓는다.
				session.setAttribute("HIT", ":" + oriNO + ":");
			}
			else {
				//	과거에 보았던 글 번호 중에서 지금 번호가 있는지 확인한다.
				int	index = oldHit.indexOf(":" + oriNO + ":");
				if(index == -1) {
					//	이글은 본적이 없다.
					isHit = true;
					//	지금까지 보았던 글번호 뒤에 지금 본 글번호를 붙어서 다시 등록한다.
					session.setAttribute("HIT", oldHit + (":" + oriNO + ":"));
				}
				else {
					isHit = false;
				}
			}
		}
		//	1.	일반적인 방식으로 상세보기를 하는 순간 조회수를 증가하도록 한다.
		if(isHit == true) {
			dao.updateHit(oriNO);
		}
		//	2.	다시 게시물을 데이터베이스에서 꺼낸다.
		ReBoardData 	data = dao.getBoardView(oriNO);
		//	모델에서 컨넥션을 닫기로 약속하자.
		dao.closeCon();
		
		req.setAttribute("DATA", data);
		//	그냥 뷰에게 알려줘라
		req.setAttribute("NOWPAGE", nowPage);
		return "../ReBoard/BoardView.jsp";
	}
}





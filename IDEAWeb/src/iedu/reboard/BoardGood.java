package iedu.reboard;

import 	javax.servlet.http.*;

import dao.ReBoardDAO;

public class BoardGood implements ReBoardMain {
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		넘어온 파라메터 받는다.
		String	strNO = req.getParameter("oriNO");
		int		oriNO = Integer.parseInt(strNO);
		//	이 파라메터는 이곳에서는 사용하지 않는 파라메터인데... 릴레이 시킬라고 받았다.
		//	왜냐하면		좋아요를 처리한 후에는 다시 상세보기를 해야 할텐데
		//					상세보기를 하기 위해서는 nowPage를 알려주어야 한다.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = Integer.parseInt(strPage);
		
		//		데이터베이스에 부탁해서 좋아요 숫자를 증가한다.
		ReBoardDAO	dao = new ReBoardDAO();
		dao.updateGood(oriNO);
		dao.closeCon();
		
		//		뷰에게 알려줄 데이터를 보낸다.
		req.setAttribute("ORINO", oriNO);
		req.setAttribute("NOWPAGE", nowPage);
		//		뷰를 선택한다.
		return "../ReBoard/BoardGood.jsp";
	}
}

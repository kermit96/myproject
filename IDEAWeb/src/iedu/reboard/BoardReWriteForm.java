package iedu.reboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardReWriteForm implements ReBoardMain {
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		넘어온 원본 글 번호를 받는다.
		String	strNO = req.getParameter("oriNO");
		int		oriNO = Integer.parseInt(strNO);
		//		폼을 보여주면 되므로 다른 작업은 필요하지 않는다.
		//		물론 권한이 필요한 요청이면 권한 검사는 처리한다.
		//		다만 답글의 경우는 원본글의 번호가 반드시 필요하므로
		//		원본글의 번호를 뷰에게 알려주도록 한다.
		//		릴레이 시킨다		라고 표현한다.
		req.setAttribute("ORINO", oriNO);
		return "../ReBoard/BoardReWriteForm.jsp";
	}
}

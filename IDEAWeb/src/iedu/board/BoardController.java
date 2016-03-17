package iedu.board;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class BoardController extends HttpServlet {
	//	요청이 오면 실행할 클래스를 가지고 있을 맵 컬렉션
	HashMap		map = new HashMap();
	public void init(ServletConfig config) throws ServletException {
		//	어떤 요청이 왔을때 무엇을 실행할지를 미리 등록해 놓고 이것을 사용하면 안될까?
		try {
			//	사용할 클래스를 로딩한다.
			Class	a = Class.forName("iedu.board.BoardList");
			Class	b = Class.forName("iedu.board.BoardInsert");
			Class	c = Class.forName("iedu.board.BoardDetail");
			Class	d = Class.forName("iedu.board.BoardModify");
			Class	e = Class.forName("iedu.board.BoardDelete");
			Class	f = Class.forName("iedu.board.BoardInsertForm");
			Class	g = Class.forName("iedu.board.BoardReWrite");
//			Class	h = Class.forName("iedu.board.BoardModifyForm");
			//	new 를 강제로 시킨다.
			Object		a1 = a.newInstance(); 
			Object		b1 = b.newInstance(); 
			Object		c1 = c.newInstance(); 
			Object		d1 = d.newInstance(); 
			Object		e1 = e.newInstance();
			Object		f1 = f.newInstance();
			Object		g1 = g.newInstance();
//			Object		h1 = h.newInstance();
			
			//	이것을 나중에 사용하기 위해서 Map에 등록해 놓는다.
			map.put("/Board/BoardList.bbs", a1);
			map.put("/Board/BoardInsert.bbs", b1);
			map.put("/Board/BoardDetail.bbs", c1);
			map.put("/Board/BoardModify.bbs", d1);
			map.put("/Board/BoardDelete.bbs", e1);
			map.put("/Board/BoardInsertForm.bbs", f1);
			map.put("/Board/BoardReWrite.bbs", g1);
//			map.put("/Board/BoardModifyForm.bbs", h1);
		}
		catch(Exception e) {
			
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
		//	이 함수는 get 방식으로 요청하면 실행되는 함수이고
		doService(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
		//	이 함수는 post 방식으로 요청하면 실행되는 함수이다.
		doService(req, resp);		
	}
	//	이곳에  get, post일때 동시에 실행되는 함수를 만들어보자
	public void doService(HttpServletRequest req, HttpServletResponse resp) {
		String		view = "";			//	모델이 선택한 뷰를 기억할 준비
		//	이곳에서 할일은
		//		요청 내용을 분석해서
		//	1.	어떻게 요청이 들어왔는지 확인한다.
		String		request = req.getRequestURI();
		//		이 함수는 요청 내용을 알아내는 함수이다.
		String		domain = req.getContextPath();
		//		이 함수는 요청 내용중 도메인으로 빠지는 부분을 알수 있다.
		
		//	이 두가지를 조합해서	실제 요청 내용을 알아낼 수 있다.
		String		realReq = request.substring(domain.length());
//		System.out.println(request);
//		System.out.println(domain);
//		System.out.println(realReq);		//	/Board/BoardList.bbs
		
		//	요청한 내용을 이용해서 사용할 클래스를 꺼내주세요.
		BoardMain	target = (BoardMain) map.get(realReq);
		//	이제 이 클래스를 실행해서 일을 분산하자.
		//	그 모델이 가지고 있는 메인 함수를 호출하는 것이다.
		//	이로써 메인 컨트롤러는 요청에 따라 원하는 모델을 실행하게 되는 것이다.
		view = target.action(req, resp);
		//	이제 모델이 실행이 끝나면 뷰에게 일처리를 부탁해야 한다.
		//	뷰는 응답 문서를 만드는 프로그램이다.		고로 이 문서는 JSP가 되어야 한다.
		//	문제는 JSP 는 웹 서버가 가지고 있는 엔진이 실행할 수 있는 문서이다.
		//	엔진에게 이 뷰문서를 실행해 달라고 부탁해야 한다.
		
		//	view 라는 변수에는 사용할 뷰 프로그램(JSP 프로그램의 이름)이 기억되어 있다.
		RequestDispatcher	dis = req.getRequestDispatcher(view);
		try {
			dis.forward(req,  resp);
		}
		catch(Exception e) {}
	}
	public void destroy() {
		
	}
}

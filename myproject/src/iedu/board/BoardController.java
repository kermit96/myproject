package iedu.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.bbs")
public class BoardController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	HashMap<String,BoarderMain>  map = new HashMap<>();


	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 	super.init(config);
		// 어떤 
		 
		map.put("/Board/BoarderList.bbs", new BorderList());
		map.put("/Board/BoarderDelete.bbs", new BorderDelete());
		map.put("/Board/BoarderDetail.bbs", new BorderDetail());
		map.put("/Board/BoarderInsert.bbs", new BorderWriteSave());
		map.put("/Board/BoarderModify.bbs", new BorderModify());
		map.put("/Board/BoardInsertForm.bbs", new BoardInsertForm());	
		map.put("/Board/BoardReWrite.bbs", new BoardReWrite());
  
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {


		// TODO Auto-generated method stub
		//	super.init();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//	super.doGet(req, resp);

		doService(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//	super.doPost(req, resp);

		doService(req,resp);
	}

	public void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{

		// 이곳에서 할일은 
		//   요청 내용을 분석해서
		//  1. 어떻게 요청이 들어왔는지 확인한다.

		
		String view="";
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");	
		String request = req.getRequestURI();

		
		PrintWriter  out = resp.getWriter();
		String domain = req.getContextPath();


		String realReq = request.substring(domain.length());

		out.println("나 실행되니==>"+request+"<br>");
		out.println("domain==>"+domain+"<br>");
		


		out.println("realReq222==>"+realReq+"<br>");
		BoarderMain target = map.get(realReq);
		
		
		if (target != null) {
			view = target.action(req, resp);	 
            // 이제 모델이 실행이 끝나면 뷰에게 일터리를 부탁해야 한다.
			// 뷰는 응답 문서를 만드는 프로그래이다. 고로 이 문서는 JSP 가 되어야 한다.
			// 문제는 JSP 는 웹 서버가 가지고 있는 엔진이 실행할 수 있는 문서이다.
			// 엔진에게 이 뷰문서를 실행해 달라고 부탁해야 한다. 
			RequestDispatcher disp =req.getRequestDispatcher(view);
			disp.forward(req, resp);
								
		} else {
			
			System.out.println("error");
			
		}

	}


}



package iedu.test;

import java.io.PrintWriter;

import 	javax.servlet.*;
import 	javax.servlet.http.*;

//	1.	이 클래스가 서블릿 클래스가 되기 위해서는 HttpServlet 클래스를 상속 받아야 한다.
public class FirstServlet extends HttpServlet {
	//	2.	라이프 사이클 함수 중에서 필요한 함수를 오버라이드 한다.
	//		init, doGet, doPost, destroy
	public void init(ServletConfig config) throws ServletException {
		//	init 함수는 2개가 있는데 이 중 파라메터가 존재하는 것으로 오버라이드 한다.
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
		//	이 함수는 서비스 함수로써 클라이언트가 GET 방식으로 요청할 때 자동 실행되는 함수이다.
		//	오늘은 이곳에서 처리하도록 한다.
		
		//	문제
		//		JSP는 응답에 관련된 작업을 자동적으로 혹은 지시어를 이용해서 처리하지만
		//		서블릿은 그런 자동화 작업이 없다.
		//		그리고 내장 객체도 존재하지 않는다.	예>	session 없다.
		//		단	req(Request), resp(Response)는 매개변수로 주기 때문에 사용할 수 있다. 
		
		//	1.	응답 방식을 강제로 지정하자.
		resp.setContentType("text/html"); 
		resp.setCharacterEncoding("EUC-KR");
		//	2.	응답을 하기 위해서는 out 이라는 객체가 필요하다.
		//		out 객체를 만들어서 사용해야 한다.
		PrintWriter	out = resp.getWriter();
		out.println("Hello World");
		out.println("헬로우 월드");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
		
	}
	public void destroy() {
		
	}
	
}




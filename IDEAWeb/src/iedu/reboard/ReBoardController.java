package iedu.reboard;

import javax.servlet.RequestDispatcher;
import 	javax.servlet.ServletConfig;
import 	javax.servlet.ServletException;
import 	javax.servlet.annotation.WebServlet;
import 	javax.servlet.http.HttpServlet;
import 	javax.servlet.http.HttpServletRequest;
import 	javax.servlet.http.HttpServletResponse;
import		java.util.*;
import		java.io.*;

/**
 * Servlet implementation class ReBoardController
 */
//	등록을 간편하게 처리해 주는 어노테이션 처리 부분
//	즉, "/ReBoardController"이 요청 내용이 되며
//	그 요청이 들어오면 이 서블릿이 실행이 된다.
@WebServlet("*.reb")
public class ReBoardController extends HttpServlet {
	//	요청과 모델을 기억할 맵을 만든다.
	HashMap		map = new HashMap();

	
	//	처음 요청이 있을 때 딱 한번만 실행될 함수이다.
	//	서블릿의 라이프 사이클
	//		1.	init			처음 요청이 있을 때 딱 한번 실행된다.
	//		2.	service	매 요청이 있을 때 실행된다.
	//		3.	destroy	서버가 다운될 때 한번만 실행된다.
    public void init(ServletConfig config) throws ServletException {
    	//	1.	Properties 파일을 읽는다.
    	Properties		prop = new Properties();
    	try {
    		//	참고
    		//		웹에서 사용하는 경로는 우리가 보고있는 경로가 아니다.
    		//		그러므로 웹에서 사용하는 경로는 반드시 풀 경로를 입력해 주어야 한다.
    		//		만약 상대 경로로 입력하면 우리가 보고있는 경로가 아닌 웹 서버가 사용하는 
    		//		경로에서 찾기 때문에 그 경로를 찾아서 입력해야 하는 불편함이 있다.
    		FileInputStream	fin = new FileInputStream("d:\\mystudy\\conf\\ReBoard.properties");
    		prop.load(fin);
    	}
    	catch(Exception e) {
    		System.out.println("에러 = " + e);
    	}
    	//	이제 Properties 파일에 있는 모든 요청과 그것에 대한 사용 모델을 맵으로
    	//	저장해 놓았다.
    	
    	//	2.	지금 읽은 모델은 문자로된 모델 이름이다.
    	//		그러므로 이것을 실행가능한 클래스 형태로 변경해 놓아야 한다.
    	Enumeration e = prop.keys();
    	while(e.hasMoreElements()) {
    		//	키값을 하나씩 꺼낸다.
    		//	그러면 요청 내용이 하나씩 나올 것이다.
    		String	key = (String) e.nextElement();
    		//	이제 이 키값이 대응하는 모델을 꺼낸다.
    		String	model = prop.getProperty(key);
    		try {
        		//	문자열로된 모델을 실행가능한 클래스 형태로 변경한다.
    			Class	cls = Class.forName(model);
    			//	이것을 new 시켜 놓는다.
    			Object		obj = cls.newInstance();
    			//	이제 이 결과를 Map 으로 저장해 놓았다가
    			//	실제 요청이 들어오면 해당 모델을 실행해 주어야 한다.
    			//	실제 요청을 처리하는 부분은 서비스 함수에서 사용해야 하므로....
    			map.put(key, obj);
    		}
    		catch(Exception e1) {
    			System.out.println("에러 = " + e1);
    		}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //	요청은 GET 방식과 POST 방식이 있고 각각에 요청에 실행되는 함수가 
    //	구분되어 있다.
    //	프로젝트에서는 GET과 POST방식이 혼합되어 작업되므로 구분이 무척 힘들다.
    //	결론	두 방식으로	들어온 경우 하나의 함수로 모아서 처리하자.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}

	public void doService(HttpServletRequest request, HttpServletResponse response) {
		//	웹에서는 가장 중요하는 변수가 Request, Response이다.
		//	고로 이 함수에서 뭔가를 처리할 때도 두개의 변수가 필요하다.
		//	이곳에서 두변수를 새롭게 만들 필요없이 받아서 사용하면 되겠다.
		
		//	할일
		//		요청 내용을 분석해서 그 요청에 해당하는 모델을 부르면 된다.
		//	요청은		http://localhost:8080/IDEAWeb/ReBoard/BoardList.reb
		//	이 중에서 우리가 필요한 것은 	/ReBoard/BoardList.reb만 필요하다.
		//	1.	전체 요청을 알아낸다.
		String	fullReq = request.getRequestURI();
		//	2.	도메인 부분(http://localhost:8080/IDEAWeb)을 알아낸다.
		String	domain = request.getContextPath();
		String	realReq = fullReq.substring(domain.length());
		//	이제 실제 요청을 알았으므로 실행할 수 있는 클래스를 꺼낼 수 있다.
		ReBoardMain	model = (ReBoardMain)map.get(realReq);
		//	이제 모델을 실행한다.
		String view = model.execute(request, response);
		//	문제	map 에서 꺼낼때는 항상 형 변환을 해서 꺼내야 한다.
		//			근데	사용할 모델의 종류는 여러가지 이다.
		//			문제	어떤 클래스로 형변환해야 하는가?
		//	해결책
		//			이런 경우를 대비해서 우리는 다형성 이라는 것을 배웠다.
		//			즉, 상위 클래스 하나를 만들고
		//			그 클래스를 이용하면 다른 클래스를 관리할 수 있다.
		//	결론	모델 클래스를  만들때도 다형성 구현을 해서 만들게 되면
		//			상위 클래스를 이용해서 모든 모델을 관리할 수 있게다.
		
		//	마지막 단계
		//		컨트롤러는 선택 된 뷰를 실행하면 된다.
		//		문제
		//			모델이 알려준 정보를 뷰에서 사용하도록 해야 한다.
		
		//	참고	뷰를 선택하는 방법
		//			redirect	현재 요청을 잊어버리고 새로운 요청을 한다.
		//				단점	모델이 알려준 정보도 같이 잊어버린다.
		//			forward	현재 요청을 유지한 상태에서 새로운 요청을 한다.
		//				장점	모델이 알려준 정보를 유지한다.
		RequestDispatcher	dis = request.getRequestDispatcher(view);
		try {
			dis.forward(request,  response);
		}
		catch(Exception e) {}
	}
}

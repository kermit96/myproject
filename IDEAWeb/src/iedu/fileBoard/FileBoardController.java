package iedu.fileBoard;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import 	javax.servlet.annotation.*;
import 	javax.servlet.http.*;

//	이것이 컨트롤러가 되기 위해서는
//	1.	HttpServlert을 상속받아서 만들어야 하며
//	2.	서버에 등록해야 한다.
@WebServlet("*.fbd")
public class FileBoardController extends HttpServlet{
	//	4개의 서블릿 라이프 함수가 존재해야 한다.
	
	//	등록된 내용을 다른 곳에서도 사용할 수 있도록 전역 변수로 만든다.
	HashMap		map = new HashMap();
	
	public void init(ServletConfig config) throws ServletException {
		//	요청과 사용할 모델을 기억해서 나중에 요청이 들어오면
		//	일을 분배할 수 있도록 작업을 해놓자.
		//	요청과 사용할 모델은	properties 파일을 이용하는 방법으로 처리하자.
	
		//	1.	프로퍼티스 파일을 읽는다.
		Properties		prop = new Properties();
    	try {
    		FileInputStream	fin = new FileInputStream("D:\\JSPSource\\IDEAWeb\\src\\iedu\\fileBoard\\fbPropertice.properties");
    		prop.load(fin);
    	}
    	catch(Exception e) {
    		System.out.println("에러 = " + e);
    	}

    	Enumeration e = prop.keys();
    	while(e.hasMoreElements()) {
    		String	key = (String) e.nextElement();
    		String	model = prop.getProperty(key);
    		try {
    			Class	cls = Class.forName(model);
    			Object		obj = cls.newInstance();
    			map.put(key, obj);
    		}
    		catch(Exception e1) {
    			System.out.println("에러 = " + e1);
    		}
    	}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	//	2가지 종류의 서비스 함수를 하나로 모아서 처리할 함수를 강제로 만들자
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//	이 함수는 실제 요청이 들어오면 요청이 들어올때마다 실행할 함수이다.
		//	그러므로 이함수에서 할 일은
		//	요청이 들어오면 그 요청에 해당하는 모델을 실행하고
		//	요청은		http://localhost:8080/IDEAWeb/FileBoard/FileBoardList.fbd		로 들어오는데
		//	프로퍼티스 파일에는 /FileBoard/FileBoardList.fbd	만 등록해 놓는다.
		//	그러므로 요청 부분에서 도메인을 뺀 나머지 부분만 알아야
		//	프로퍼티스에 등록한 내용을 알 수 있다.
		//		1)	전체 요청을 알아내고
		String	fullReq = request.getRequestURI();
		//		2)	도메인 부분을 알아내서
		String	domain = request.getContextPath();
		//		3)	전체 요청 중 도메인 부분을 제외한 나머지 부분만 알아내야 한다.
		String	realReq = fullReq.substring(domain.length());
		//	2.	이 요청에 해당하는 모델을 꺼낸다.
		FileBoardMain model = (FileBoardMain) map.get(realReq);
		//		꺼낼때는 형변환을 해서 꺼내야 한다.
		//		근데 모델 클래스는 다 다르므로 형변환도 원칙적으로 다르게 해야한다.
		//		방법
		//			다형성 구현을 처리해서 상위 클래스로 형 변환해서 꺼내도록 한다.
		String view = model.service(request, response);	
		//			참고로 왜 반환값을 받느냐?
		//			모델이 실행한 후에는 뷰를 실행해야 하는데......
		//				모델 작업 도중에 뷰는 변경될 수 있다.
		//				그래서 모델 작업 도중에 뷰를 선택하도록 하기 위해서.....
		
		//	모델 실행이 끝나면 뷰를 실행한다.
		//	모델에서 알려준 데이터를 이용할 수 있도록 뷰를 실행해야 한다.
		//	뷰를 실행하는 방법			Redirect	데이터를 이용할 수 없다.
		//									forward	데이터를 이용할 수 있다.
		//		RequestDispatcher를 이용해서 forward 방식으로 전환할 수 있다.
		
		RequestDispatcher	dis = request.getRequestDispatcher(view);
		try {
			dis.forward(request,  response);
		}
		catch(Exception e) {}
	}
	public void destroy() {	
	}
}



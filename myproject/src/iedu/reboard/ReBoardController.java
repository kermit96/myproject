package iedu.reboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import iedu.config.ConfigFileHandler;

/**
 * Servlet implementation class ReBoardController
 */
@WebServlet("/reboard/*.reg")
public class ReBoardController extends HttpServlet {
	
	private  Map<String,ReboardInterfeace> m_map = new HashMap<String,ReboardInterfeace>() ;      
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReBoardController() {
        super();               
        // TODO Auto-generated constructor stub
        
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String view="";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String requeststr = request.getRequestURI();

		
		PrintWriter  out = response.getWriter();
		String domain = request.getContextPath();


		String realReq = requeststr.substring(domain.length());

		ReboardInterfeace target = m_map.get(realReq);
		
		
		if (target != null) {
			view = target.Service(request, response);	 
            // 이제 모델이 실행이 끝나면 뷰에게 일터리를 부탁해야 한다.
			// 뷰는 응답 문서를 만드는 프로그래이다. 고로 이 문서는 JSP 가 되어야 한다.
			// 문제는 JSP 는 웹 서버가 가지고 있는 엔진이 실행할 수 있는 문서이다.
			// 엔진에게 이 뷰문서를 실행해 달라고 부탁해야 한다. 
			RequestDispatcher disp =request.getRequestDispatcher(view);
			disp.forward(request, response);
								
		} else {
			
			System.out.println("error");
			
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	 	super.init(config);
	 	
	 	ConfigFileHandler  handler=  ConfigFileHandler.getConfigFileHandler("reboard.property");
	 	
	 	while(handler.propertyNames().hasMoreElements()) 
	 	{
	 		String name = (String)handler.propertyNames().nextElement();
	 		
	 		String classname = handler.getValue(name);
	 		try {
	 			Class cls = Class.forName(classname);
	 			ReboardInterfeace obj =(ReboardInterfeace) cls.newInstance();
	 			m_map.put(name, obj);
			} catch (Exception e) {
				// TODO: handle exception
			}
	 			 			 		    	 		
	 	}
	 	
//	 	m_map.put("", value)
	}
}

package iedu.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet(name = "loginrequest", urlPatterns = { "/memberservelet/login" })
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 만약 로그인 중이면  
		//  /member/loginresult.jsp 을 부른다 
		// 만약 로그인을 한다고 하면  /member/loginForm.jsp 을 부른다. 
		HttpSession   session = request.getSession();
		
		String url ="";

		String userid = (String)session.getAttribute("id");

		if (userid !=null && !userid.isEmpty() ) {
			
			 url = (String)session.getAttribute("url");
			if (url != null &&  !url.isEmpty())  {
				session.setAttribute("url", "");	        			
			} else {				
				url = "/mian/index.jsp";
			}
						
		} else {			
		  	  url = "/Member/LoginForm.jsp";		  	  
		}
		
		RequestDispatcher disp =request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

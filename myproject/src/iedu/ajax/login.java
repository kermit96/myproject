package iedu.ajax;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iedu.dao.LoginDao;

/**
 * Servlet implementation class login
 */
@WebServlet(description = "login", urlPatterns = { "/ajax/login" })
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
// 		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	    LoginDao login = new LoginDao();  

	    String userid = request.getParameter("userid");
	    
	    String password = request.getParameter("password");
	    
	    System.out.println("userid="+userid);
	    System.out.println("password="+password);
	    
	    HashMap map ; 	    
	    map = login.isMember(userid, password);
	    System.out.println("map size="+map.size());
	    HttpSession   session = request.getSession(true);
	    if (!map.isEmpty()) {
	 	      session.setAttribute("id", map.get("id"));
	 	      session.setAttribute("name", map.get("name"));
	 	      session.setAttribute("nick", map.get("nick"));	      	  
	 	      String url = (String)session.getAttribute("url");
	 	      
	 	      if (url != null &&  !url.isEmpty())  {
	 	    	  session.setAttribute("url", "");	        
	 	    	  response.sendRedirect(url);
	 	    	  return;
	 	      }
	 	      
	    }

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

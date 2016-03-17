package iedu.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.config.ConfigFileHandler;

/**
 * Servlet implementation class dirsave
 */
@WebServlet("/ajax/dirsave")
public class dirsave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dirsave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 String dir = request.getParameter("dir");
		 
		 boolean ret = ConfigFileHandler.SaveInitDirectory(dir);
		 if (ret == false) {			 
			 response.getWriter().print("저장 하지 못했습니다.");			 
		 }
		 else {			 
			 response.getWriter().print("저장 했습니다.");
		 }
		
		 
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
			
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package iedu.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.config.Globalconfig;

/**
 * Servlet implementation class dbsave
 */
@WebServlet("/ajax/globalsave")
public class globalsave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public globalsave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		  
		int  dbtype = 0 ;
		String dbname ="";
		String host ="";
		int     port = 0 ;
		String userid ="" ;				
		String password="";
		String savedir="";
		
		dbtype = Integer.parseInt(request.getParameter("dbtype") );
		dbname = request.getParameter("dbname");
		 host = request.getParameter("dbhost");
		 port = Integer.parseInt(request.getParameter("dbport"));
		 userid = request.getParameter("dbuser");
		 password = request.getParameter("dbpassword");
		 savedir = request.getParameter("savedir");
		 
		 
		Globalconfig config = new Globalconfig();
		
		config.setDbtype(dbtype);
		config.setDbname(dbname);
		config.setHost(host);
		config.setPort(port);;
		config.setUserid(userid);
		config.setPassword(password);
		config.setSavedir(savedir);
		
		config.Save();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		
		
		out.print("저장 했습니다");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

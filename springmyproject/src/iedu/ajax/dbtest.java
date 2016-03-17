package iedu.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.sql.BaseJDBCDao;
import iedu.sql.DBTYPE;

/**
 * Servlet implementation class dbtest
 */
@WebServlet("/ajax/dbtest")
public class dbtest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dbtest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	// 	response.getWriter().append("Served at: ").append(request.getContextPath());
		int  dbtype = 0 ;
		String dbname ="";
		String host ="";
		int     port = 0 ;
		String userid ="" ;				
		String password="";
		
		dbtype = Integer.parseInt(request.getParameter("dbtype") );
		dbname = request.getParameter("dbname");
		 host = request.getParameter("dbhost");
		 port = Integer.parseInt(request.getParameter("dbport"));
		 userid = request.getParameter("dbuser");
		 password = request.getParameter("dbpassword");
				
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String str = getTestString(dbtype,dbname,host,port,userid,password);
		out.print(str);
	}
	
	
	private  String getTestString(int dbtype,String dbname,String host,int port,
			  String userid,String password)
	{
		
		 DBTYPE type = DBTYPE.fromInt(dbtype);     
		 try {
		    BaseJDBCDao dao = BaseJDBCDao.GetjdbcDao(type, host, port, dbname, userid, password);
		    dao.closeAll();
		    return "Success";
		 } catch(Exception ex) {
			 
			 return ex.toString(); 
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

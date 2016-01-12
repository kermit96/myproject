package iedu.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import iedu.dao.MemberDao;
import iedu.data.memberdata;

/**
 * Servlet implementation class userinfo
 */
@WebServlet("/ajax/userinfo")
public class userinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 // 	response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
	
		
		JsonObject obj = new JsonObject();
		
		MemberDao dao = new MemberDao();   
		memberdata data = new memberdata();
		
		data.userid = (String)request.getSession().getAttribute("id");
		
		dao.GetMmeber(data);
		
		obj.addProperty("name",data.name );
		obj.addProperty("nickname", data.nickname);		
		obj.addProperty("tel", data.tel);
		response.getWriter().print(obj.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

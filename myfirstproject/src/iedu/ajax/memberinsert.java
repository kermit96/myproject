package iedu.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.dao.MemberDao;
import iedu.data.memberdata;
import iedu.util.util;

/**
 * Servlet implementation class memberinsert
 */
@WebServlet("/ajax/memberinsert")
public class memberinsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberinsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	// 	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String userid = request.getParameter("userid");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		
		memberdata data = new  memberdata();
	    data.userid = userid;
	    data.name = name;
	    data.tel = tel;
	    data.password = util.GetSha256(password) ;
	    data.nickname = nickname;
	    
	    MemberDao dao = new MemberDao();
	    dao.InsertMember(data); 
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package iedu.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.dao.LoginDao;
import iedu.dao.MemberDao;
import iedu.data.memberdata;

/**
 * Servlet implementation class membermmodify
 */
@WebServlet("/ajax/membermodify")
public class membermmodify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public membermmodify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String tel = request.getParameter("tel");
		String id = (String)request.getSession().getAttribute("id");		
//		String password = request.getParameter("password");
		
		MemberDao dao = new MemberDao();
	 
		 memberdata data = new memberdata();
		 
		 data.name = name;
         data.nickname = nickname;
         data.tel = tel;
         data.userid = id;
		 
		try {
		     dao.UpdateMember(data);		    
		} catch (Exception ex) {
			ex.printStackTrace();		
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

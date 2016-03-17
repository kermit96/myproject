package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.ReBoardDAO;
import dao.ReBoardSQL;
import iedu.data.ReBoardData;

/**
 * Servlet implementation class reboarddelete
 */
@WebServlet("/ajax/reboarddelete")
public class reboarddelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reboarddelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	// 	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		// 사용자가 로그인 안했으면 삭제 할수 없다. 
		
		// 그리고 작성자가 아니면 삭제할 수 없다 . 
		
		JSONObject obj =  new JSONObject();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
	
		PrintWriter out=response.getWriter();
		
		String userid = (String)request.getSession().getAttribute("ID");
		if (userid == null)
			userid ="";
		if (userid.isEmpty()) {
			obj.put("result", false);
			obj.put("reason", "로그인을 하지 않았습니다.");
			out.print(obj.toJSONString());
			return;
		}
		
		int orino = Integer.parseInt(request.getParameter("no"));
		ReBoardDAO	dao = new ReBoardDAO();
		dao.delete(orino,userid);
	
		obj.put("result", true);
		obj.put("reason", "");
		out.print(obj.toJSONString());

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReBoardDAO;

/**
 * Servlet implementation class goodclick
 */
@WebServlet("/ajax/goodclick")
public class goodclick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public goodclick() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
	
		PrintWriter out=response.getWriter();
	   
		String	strNO = request.getParameter("no");
		int OriNo = Integer.parseInt(strNO);
		System.out.println("oriNo="+OriNo);
		//		데이터베이스에 부탁해서 좋아요 숫자를 증가한다.
		ReBoardDAO	dao = new ReBoardDAO();
		int count = dao.updateGood(OriNo);		
		out.print(count);		
		dao.closeCon(); 		 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

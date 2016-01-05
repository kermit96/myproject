package iedu.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.util.util;

/**
 * Servlet implementation class ase256
 */
@WebServlet("/ajax/ase256")
public class ase256 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ase256() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String reqstr = request.getParameter("str");
		System.out.println(reqstr);
		
		String str = "";
		try {
			str = iedu.util.ase256.AES_Encode(str);		
		} catch (Exception ex) {
		   ex.printStackTrace();				
		}
		
		
		
		PrintWriter out=response.getWriter();

		out.print(str);        // out.print 내용을 ajax의 dataType이 jason인 놈에게 데이터 쏴줌
				     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

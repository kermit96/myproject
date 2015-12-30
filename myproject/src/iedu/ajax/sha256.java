package iedu.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.util.util;

@WebServlet("/ajax/sha256")
public class sha256  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4666091942774567250L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doGet(req, resp);
		resp.setContentType("text/html;charset=utf-8");

		String reqstr = req.getParameter("str");
		System.out.println(reqstr);
		String str = util.GetSha256(reqstr);
		 
		PrintWriter out=resp.getWriter();

		out.print(str);        // out.print 내용을 ajax의 dataType이 jason인 놈에게 데이터 쏴줌
				  
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doPost(req, resp);
		System.out.println("post");
		doGet(req,resp);
	}
}

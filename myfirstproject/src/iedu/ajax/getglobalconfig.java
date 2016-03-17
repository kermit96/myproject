package iedu.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import iedu.config.Globalconfig;

/**
 * Servlet implementation class getdbconfig
 */
@WebServlet("/ajax/getglobalconfig")
public class getglobalconfig extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getglobalconfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		Globalconfig config = new Globalconfig();

		Gson gson = new Gson();

		String jsonstr = gson.toJson(config);

		PrintWriter out = response.getWriter();
		out.print(jsonstr); // out.print 내용을 ajax의 dataType이 jason인 놈에게 데이터 쏴줌
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

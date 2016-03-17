package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import dao.ReBoardDAO;
import iedu.data.HitInfo;

/**
 * Servlet implementation class badclick
 */
@WebServlet("/ajax/badclick")
public class badclick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public badclick() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
	
		
	   
		String	strNO = request.getParameter("no");
		int OriNo = Integer.parseInt(strNO);

		//		데이터베이스에 부탁해서 좋아요 숫자를 증가한다.
		ReBoardDAO	dao = new ReBoardDAO();
		HitInfo count = dao.updateBad(OriNo);
	

 	   Gson gson = new Gson();

       String jsonstr = gson.toJson(count);
			 
	   PrintWriter out=response.getWriter();
 	   out.print(jsonstr);        // out.print 내용을 ajax의 dataType이 jason인 놈에게 데이터 쏴줌
 		
    	 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}






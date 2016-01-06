package iedu.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import iedu.dao.LoginDao;
import iedu.data.LoginResulttData;
import iedu.util.util;


/**
 * Servlet implementation class login
 */
@WebServlet(description = "login", urlPatterns = { "/ajax/login" })
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 *  
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 		response.getWriter().append("Served at: ").append(request.getContextPath());

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		
	
	   String userid = request.getParameter("userid");
	   String password = request.getParameter("password");
	   HashMap map ; 
	   LoginDao login = new LoginDao();  
	   map = login.isMember(userid, password);
	   HttpSession  session =  request.getSession();
	   LoginResulttData data= new LoginResulttData();
	   
	   if (!map.isEmpty()) {
		      session.setAttribute("id", map.get("id"));
		      session.setAttribute("name", map.get("name"));
		      session.setAttribute("nick", map.get("nick"));	      	  
		      String url = (String)session.getAttribute("url");		
		      session.setAttribute("url", "");
		      
		      if (url==null)
		    	  url ="";
		      if ( url.isEmpty())  {
		    	  	url="../member/login.do";        		    
		      }
		      
		      data.isSuccess = true;
		      data.url = url;
		      		      
	   } else {
		    data.isSuccess = false;
		    data.failreason = "로그인 유저가 없거나 패스워드가 잘못되어 있습니다. ";		   
	   }
	   

	   Gson gson = new Gson();

       String jsonstr = gson.toJson(data);
			 
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

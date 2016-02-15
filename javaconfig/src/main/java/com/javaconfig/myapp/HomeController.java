package com.javaconfig.myapp;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.javaconfig.myapp.dao.SampleDAO;


import iedu.config.Globalconfig;
import iedu.sql.BaseJDBCDao;
import iedu.sql.DBTYPE;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	SampleDAO		sampleDAO;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model ,HttpSession ses) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		int		total	= sampleDAO.getTotal();
		System.out.println(total);
		return "home";
	}
	
	
	@RequestMapping(value = "/Setup/setup")
	public String Setup(Locale locale, Model model) {

		return "Setup/setup";
	}
	
	
	
	
	@RequestMapping(value = "/ajax/getglobalconfig")
	public void getglobalconfig(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	//	response.setContentType("text/html");
	//	response.setCharacterEncoding("utf-8");

		
		Globalconfig config = new Globalconfig();

		Gson gson = new Gson();

		String jsonstr = gson.toJson(config);

		PrintWriter out = response.getWriter();
		out.print(jsonstr); // out.print 내용을 ajax의 dataType이 jason인 놈에게 데이터 쏴줌		
		
	}
	
	
	
	@RequestMapping(value = "/ajax/globalsave")
	public void globalsave(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		  
		int  dbtype = 0 ;
		String dbname ="";
		String host ="";
		int     port = 0 ;
		String userid ="" ;				
		String password="";
		String savedir="";
		
		dbtype = Integer.parseInt(request.getParameter("dbtype") );
		dbname = request.getParameter("dbname");
		 host = request.getParameter("dbhost");
		 port = Integer.parseInt(request.getParameter("dbport"));
		 userid = request.getParameter("dbuser");
		 password = request.getParameter("dbpassword");
		 savedir = request.getParameter("savedir");
		 
		 
		Globalconfig config = new Globalconfig();
		
		config.setDbtype(dbtype);
		config.setDbname(dbname);
		config.setHost(host);
		config.setPort(port);;
		config.setUserid(userid);
		config.setPassword(password);
		config.setSavedir(savedir);
		
		config.Save();
	//	response.setContentType("text/html");
	//	response.setCharacterEncoding("utf-8");
		
		
		PrintWriter out = response.getWriter();
		
		
		
		out.print("저장 했습니다");
		
		
		
	}
	
	
	@RequestMapping(value = "/ajax/dbtest")
	public void dbtest(HttpServletRequest request, HttpServletResponse response)
	{	
		int  dbtype = 0 ;
		String dbname ="";
		String host ="";
		int     port = 0 ;
		String userid ="" ;				
		String password="";
		
		dbtype = Integer.parseInt(request.getParameter("dbtype") );
		dbname = request.getParameter("dbname");
		 host = request.getParameter("dbhost");
		 port = Integer.parseInt(request.getParameter("dbport"));
		 userid = request.getParameter("dbuser");
		 password = request.getParameter("dbpassword");
				
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out= null;
		
		try {
		 out = response.getWriter();
		
		} catch(Exception ex) { 
			
			
		}
		
		String str = getTestString(dbtype,dbname,host,port,userid,password);
		out.print(str);
	}
	
	private  String getTestString(int dbtype,String dbname,String host,int port,
			  String userid,String password)
	{		
		 DBTYPE type = DBTYPE.fromInt(dbtype);     
		 try {
		    BaseJDBCDao dao = BaseJDBCDao.GetjdbcDao(type, host, port, dbname, userid, password);
		    dao.closeAll();
		    return "Success";
		 } catch(Exception ex) {
			 
			 return ex.toString(); 
		 }
	}

	
}

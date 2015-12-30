<%@page import="iedu.dao.DBTYPE"%>
<%@page import="iedu.dao.LoginDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<body>
<%

    LoginDao login = new LoginDao();  

   String userid = request.getParameter("userid");
   
   String password = request.getParameter("password");
   
   System.out.println("userid="+userid);
   System.out.println("password="+password);
   
   HashMap map ; 
   
   map = login.isMember(userid, password);
   System.out.println("map size="+map.size());
   if (!map.isEmpty()) {
	      session.setAttribute("id", map.get("id"));
	      session.setAttribute("name", map.get("name"));
	      session.setAttribute("nick", map.get("nick"));	      	  
	      String url = (String)session.getAttribute("url");
	      System.out.println(url);
	      if (url != null &&  !url.isEmpty())  {
	    	  session.setAttribute("url", "");	        
	    	  response.sendRedirect(url);
	    	  return;
	      }
	      
   }
   
  response.sendRedirect("LoginForm.jsp");
  
   

	//	이제 결과를 보여줄 차례이다.
	//	JSP는 항상 실행 결과를 클라이언트에 전달해 주어야 한다.
	//	원칙적으로 JSP는 뭔가를 처리했으면 그 결과를 클라이언트에게 알려주어야 한다.
	//	하지만 대부분의 경우 로그인 후에는 다시 메인 화면으로 응답을 하게 되더라
	//	이 말은 서버가 응답을 하는 대신에 메인 화면으로 응답하도록 요청을 바꾼것이다.
	//	서버가 다른 응답을 하도록 하는 것을
	//	우리가 Redirect 라고 부르고
	//	이것을 가능하게 하는 명령			response.sendRedirect() 이다.
	
	//	이렇게 명령을 내리면 서버는 다시 LoginForm.jsp를 실행해서 그 결과를 응답하게 된다. 
%>
	</body>
</html>









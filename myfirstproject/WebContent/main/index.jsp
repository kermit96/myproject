<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<%	   
	String	tempID = (String) session.getAttribute("id");
	if(tempID == null || tempID.length() == 0) {
		
		String url =request.getRequestURL().toString();
		
		session.setAttribute("url", url );
	/*	
		RequestDispatcher rd =
				  request.getRequestDispatcher("Member/LoginForm.jsp");
		rd.forward(request,response);
		*/
		
				 
//		response.sendRedirect("member/login.do");
				
	}
	%>
	
	<body>
      <%@ include file="../include/top.jsp" %>
     
	</body>
</html>
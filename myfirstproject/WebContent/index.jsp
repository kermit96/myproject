<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		
				 
		response.sendRedirect("memberservelet/login");
				
	}  else {
		
		response.sendRedirect("main/index.jsp");
	}
	%>
	

</html>
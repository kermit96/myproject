<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	할일
	//		로그인을 했을때 주어진 모든 정보(세션에 있는 정보)를 삭제시키고
	session.removeAttribute("ID");	
	session.removeAttribute("NAME");		
	session.removeAttribute("NICK");	
	//		다시 로그인 화면으로 보내면 된다.
	response.sendRedirect("LoginForm.jsp");
%>
	</body>
</html>
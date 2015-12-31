<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
나는 잘생겼다.
<%
	//	위의 내용은 데이터베이스에 기록하는 소스라고 가정하자.
	
	//	서버가 강제로 요청을 변경한다.
	response.sendRedirect("RedirectTest.jsp");
%>
	</body>
</html>
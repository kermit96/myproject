<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	if(session.isNew()) {
		out.println("새로운 손님이군요<br>");		
		out.println("당신의 일련 번호는 " + session.getId());		
	}
	else {
		out.println("당신의 일련 번호는 " + session.getId());		
	}
%>
	</body>
</html>
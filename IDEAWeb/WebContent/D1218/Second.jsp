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
		out.println("���ο� �մ��̱���<br>");		
		out.println("����� �Ϸ� ��ȣ�� " + session.getId());		
	}
	else {
		out.println("����� �Ϸ� ��ȣ�� " + session.getId());		
	}
%>
	</body>
</html>
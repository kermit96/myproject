<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	����
	//		�α����� ������ �־��� ��� ����(���ǿ� �ִ� ����)�� ������Ű��
	session.removeAttribute("ID");	
	session.removeAttribute("NAME");		
	session.removeAttribute("NICK");	
	//		�ٽ� �α��� ȭ������ ������ �ȴ�.
	response.sendRedirect("LoginForm.jsp");
%>
	</body>
</html>
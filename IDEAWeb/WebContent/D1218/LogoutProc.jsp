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
	//		�α����� �� ���� ���� ���� ���� CID��� Ű������ �����Ͱ� �ִ��� ���θ� ���� �Ǵ��Ѵ�.
	//		�α׾ƿ���?
	//		CID��� Ű���� �����͸� ����� �ɰ��̴�.
	session.removeAttribute("CID");

	response.sendRedirect("LoginForm.jsp");
%>
	</body>
</html>
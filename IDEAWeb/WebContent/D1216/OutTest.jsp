<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
//	���� ��ü�� �ڹ����� �����̹Ƿ� �ڹ����� ��Ҹ� ����ϴ� ������ ����ؾ� �Ѵ�.
//	���� ��ü�� �ݵ�� ��ũ��Ʈ �� ���¿��� ����ؾ� �Ѵ�.
	String	name = "ȫ�浿";

	out.println("<table border='1'>");		//	HTML�� <table>�� ���������.
	out.println("<tr>");
	out.println("<td>");
	out.println(name);
	out.println("</td>");
	out.println("</tr>");
	out.println("</table>");

%>
<table>
<%
	for(int i = 2; i < 10; i++) {
		out.println("<tr>");
		for(int j = 1; j < 10; j++) {
			out.println("<td>");
			out.println(i + " * " + j + " = " + (i * j));
			out.println("</td>");
		}
		out.println("</tr>");
	}
%>
</table>








	</body>
</html>
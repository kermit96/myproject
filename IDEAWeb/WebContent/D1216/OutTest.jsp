<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
//	내장 객체도 자바적인 변수이므로 자바적인 요소를 사용하는 곳에서 사용해야 한다.
//	내장 객체는 반드시 스크립트 릿 상태에서 사용해야 한다.
	String	name = "홍길동";

	out.println("<table border='1'>");		//	HTML에 <table>이 만들어진다.
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
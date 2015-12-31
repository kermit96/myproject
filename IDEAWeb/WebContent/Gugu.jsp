<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table>
<%	
for(int dan = 2; dan < 10; dan++) {
%>
		<tr>
<%
			for(int i = 1; i < 10; i++) {
%>				
				<td><%= dan + " * " + i + " = " + (dan * i) %></td>
<%
			}
%>
		</tr>
<%
}
%>
	</table>
</body>
</html>
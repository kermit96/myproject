<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%	
	String	id = (String)session.getAttribute("ID");
	if(id == null || id.length() == 0) {
%>	
		<form method="POST" action="LoginProc.jsp">
			<table width="50%" border="1" align="center">
				<tr>
					<td>ID</td>
					<td>
						<input type="text" name="id">
					</td>
				</tr>			
				<tr>
					<td>Password</td>
					<td>
						<input type="password" name="pw">
					</td>
				</tr>			
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="�α���">
					</td>
				</tr>			
			</table>
		</form>
<%
	}
	else {
%>		
		<table border="1" width="50%" align="center">
			<tr>
				<td align="center">
					<%= session.getAttribute("NICK") %> �� ȯ���մϴ�.
				</td>
			</tr>
			<tr>
				<td align="center">
					<a href="LogoutProc.jsp">�α׾ƿ�</a>
					<a href="http://localhost:8080/IDEAWeb">��������</a>
				</td>
			</tr>
		</table>		
<%
	}
%>
	</body>
</html>

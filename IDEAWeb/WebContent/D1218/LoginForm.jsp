<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	String	tempID = (String) session.getAttribute("CID");
	if(tempID == null || tempID.length() == 0) {
%>
		<form method="POST" action="LoginProc.jsp">
			<table border="1" width="50%" align="center">
				<tr>
					<td width="20%">ID</td>
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
					<td align="center" colspan="2">
						<input type="submit" value="로그인">
					</td>
				</tr>
			</table>		
		</form>
<%
	}
	else {
%>		
		<table width="50%" border="1" align="center">
			<tr>
				<td> <%= tempID %> 님 환영합니다.</td>
			</tr>
			<tr>
				<td align="center">
					<a href="LogoutProc.jsp">로그아웃</a>
				</td>
			</tr>
		</table>		
<%
	}
%>		
	</body>
</html>





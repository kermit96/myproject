<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<style>
			table {
   				border-collapse: collapse;
   				width:50%;
			}
			table, th, td {
				border: 1px solid black;
			}
		</style>
	</head>
	<body>
		<form method="POST" action="Result.jsp">
			<table align="center">
				<tr>
					<td width="20%">아이디</td>
					<td width="80%">
						<input type="text" name="id">
					</td>
				</tr>		
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="pass">
					</td>
				</tr>		
<%--
	자바 스크립트를 이용해서 나이는 반드시 숫자만 입력하도록 규칙을 정했다.
--%>
				<tr>
					<td>나이</td>
					<td>
						<input type="text" name="age">
					</td>
				</tr>		
				<tr>
					<td>전화번호</td>
					<td>
						<input type="text" name="phone1">-<input type="text" name="phone2">-<input type="text" name="phone3">
					</td>
				</tr>		
				<tr>
					<td>취미</td>
					<td>
						<input type="checkbox" name="hobby" value="음악">음악감상
						<input type="checkbox" name="hobby" value="독서">독서
						<input type="checkbox" name="hobby" value="음주">음주
					</td>
				</tr>		
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인">
					</td>
				</tr>		
			</table>
		</form>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%--	�Խù��� ����� �� �ִ� �۾��� ���� ������. --%>
		<form method="POST" action="../Board/BoardInsert.bbs">
			<table width="50%" border="1" align="center">
				<tr>
					<td>�۾���</td>
					<td>
						<input type="text" name="writer" disabled value="<%= session.getAttribute("ID") %>">
					</td>
				</tr>
				<tr>
					<td>����</td>
					<td>
						<input type="text" name="title">
					</td>
				</tr>
				<tr>
					<td>����</td>
					<td>
						<textarea name="body" cols="50" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="�۾���">
					</td>
				</tr>
			</table>		
		</form>
	</body>
</html>
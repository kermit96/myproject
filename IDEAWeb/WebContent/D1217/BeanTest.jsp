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
					<td width="20%">���̵�</td>
					<td width="80%">
						<input type="text" name="id">
					</td>
				</tr>		
				<tr>
					<td>��й�ȣ</td>
					<td>
						<input type="password" name="pass">
					</td>
				</tr>		
<%--
	�ڹ� ��ũ��Ʈ�� �̿��ؼ� ���̴� �ݵ�� ���ڸ� �Է��ϵ��� ��Ģ�� ���ߴ�.
--%>
				<tr>
					<td>����</td>
					<td>
						<input type="text" name="age">
					</td>
				</tr>		
				<tr>
					<td>��ȭ��ȣ</td>
					<td>
						<input type="text" name="phone1">-<input type="text" name="phone2">-<input type="text" name="phone3">
					</td>
				</tr>		
				<tr>
					<td>���</td>
					<td>
						<input type="checkbox" name="hobby" value="����">���ǰ���
						<input type="checkbox" name="hobby" value="����">����
						<input type="checkbox" name="hobby" value="����">����
					</td>
				</tr>		
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="�α���">
					</td>
				</tr>		
			</table>
		</form>
	</body>
</html>


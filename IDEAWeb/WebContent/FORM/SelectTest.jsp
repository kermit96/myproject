<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<form method="POST" action="Result.jsp">
			<select name="kind" size="3">
				<option value="�ҳ�Ÿ">�ҳ�Ÿ</option>
				<option value="Ƽ��">Ƽ��</option>
				<option value="����">����</option>
				<option value="������">������</option>
				<option value="���������">���������</option>
			</select><br>
			<textarea name="area" rows="10" cols="50"></textarea>
			<input type="submit" value="������">
		</form>
	</body>
</html>



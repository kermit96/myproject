<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<form method="POST" action="Result.jsp">
<%--
			�Է� ��ҿ��� �ݵ�� name �Ӽ��� �־�� �� �ȿ� �����Ͱ� ������ ������ �ȴ�.
--%>
			<input type="text" name="irum"><br>
			<input type="radio" name="gender" value="M">����
			<input type="radio" name="gender" value="W">����<br>
			<input type="checkbox" name="hobby1" value="���ǰ���">���ǰ���
			<input type="checkbox" name="hobby2" value="����">����
			<input type="checkbox" name="hobby3" value="����">����<br>
			<input type="submit" value="�����ϱ�">
		</form>
	</body>
</html>







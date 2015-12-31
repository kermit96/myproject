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
			입력 요소에는 반드시 name 속성이 있어야 그 안에 데이터가 서버에 전달이 된다.
--%>
			<input type="text" name="irum"><br>
			<input type="radio" name="gender" value="M">남자
			<input type="radio" name="gender" value="W">여자<br>
			<input type="checkbox" name="hobby1" value="음악감상">음악감상
			<input type="checkbox" name="hobby2" value="독서">독서
			<input type="checkbox" name="hobby3" value="여행">여행<br>
			<input type="submit" value="전달하기">
		</form>
	</body>
</html>







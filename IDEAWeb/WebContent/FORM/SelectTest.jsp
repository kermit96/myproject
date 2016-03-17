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
				<option value="소나타">소나타</option>
				<option value="티코">티코</option>
				<option value="벤츠">벤츠</option>
				<option value="포르쉐">포르쉐</option>
				<option value="람보르기니">람보르기니</option>
			</select><br>
			<textarea name="area" rows="10" cols="50"></textarea>
			<input type="submit" value="보내기">
		</form>
	</body>
</html>



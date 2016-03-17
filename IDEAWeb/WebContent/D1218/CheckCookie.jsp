<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
	당신이 가지고 있는 쿠키는 다음과 같습니다.<br>
<%
	//	필요하면 쿠키를 가지고 와서 확인하자.
	Cookie[]		cooks = request.getCookies();
	for(int i = 0; i < cooks.length; i++) {
		String	key = cooks[i].getName();
		String	value = cooks[i].getValue();
		out.println(key + " : " + value + "<br>");
	}
%>
	</body>
</html>

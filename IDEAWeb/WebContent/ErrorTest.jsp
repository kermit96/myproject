<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page errorPage="Error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	int		num = (int) (Math.random() * (100));
	if(num % 2 == 0) {
		throw new Exception();
	}
%> 
이 부분이 나오면 곤란합니다. 발생한 수는 <%= num %>
</body>
</html>




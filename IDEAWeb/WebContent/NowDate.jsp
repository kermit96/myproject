<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	Calendar	car = Calendar.getInstance();
	SimpleDateFormat	form = new SimpleDateFormat("YYYY년 MM월 dd일 hh시 mm분 ss초");
	String	result = form.format(car.getTime());
%>
지금 시간은 <b><%= result %></b> 입니다.
</body>
</html>



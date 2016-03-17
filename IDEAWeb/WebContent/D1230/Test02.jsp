<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	모델이 데이터를 생산해 주었다고 가정하기 위해서 자바 소스를 이용해서
	//	데이터를 생산한다.
	float	avg = 56.7F;
	request.setAttribute("AVG", avg);
	
	Date	d = new Date();
	request.setAttribute("DAY", d);
	
%>
	<fmt:setLocale value="en_US" />
	평균 점수는 <fmt:formatNumber value="${AVG}" type="currency" />입니다.<br>
	평균 점수는 <fmt:formatNumber value="${AVG}" pattern="#,###.00" />입니다.<br>
	<fmt:setTimeZone value="GMT-8" />
	오늘 날짜는 <fmt:formatDate value="${DAY}" type="time" />입니다.<br>
	오늘 날짜는 <fmt:formatDate value="${DAY}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" />입니다.<br>
	</body>
</html>





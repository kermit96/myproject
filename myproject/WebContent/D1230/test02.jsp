<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery.min.js">
	
</script>
<script>
	$(document).ready(function() {
	});
</script>
</head>
<%
	float avg = 12212.789F;
	request.setAttribute("AVG", avg);
	
	Date d = new Date();
	request.setAttribute("DAY",d);
%>

<body>
<fmt:setLocale value="en_us"/>
	평균 점수는
	<fmt:formatNumber type="number" maxIntegerDigits="10" value="${AVG}" />
	--
	<fmt:formatNumber type="currency" maxFractionDigits="2" value="${AVG}" />
	입니다. <br>
패턴


<fmt:formatNumber   pattern="#,####.00"  value="${AVG}" /> <br>
<fmt:setTimeZone value="GMT-20"/>

  오늘 날짜는 <fmt:formatDate value="${DAY}"   pattern="yyyy-MM-dd hh:mm:ss"/>
  set location 
  
     
</body>
</html>
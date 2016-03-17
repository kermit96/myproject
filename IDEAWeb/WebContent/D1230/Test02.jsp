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
	//	���� �����͸� ������ �־��ٰ� �����ϱ� ���ؼ� �ڹ� �ҽ��� �̿��ؼ�
	//	�����͸� �����Ѵ�.
	float	avg = 56.7F;
	request.setAttribute("AVG", avg);
	
	Date	d = new Date();
	request.setAttribute("DAY", d);
	
%>
	<fmt:setLocale value="en_US" />
	��� ������ <fmt:formatNumber value="${AVG}" type="currency" />�Դϴ�.<br>
	��� ������ <fmt:formatNumber value="${AVG}" pattern="#,###.00" />�Դϴ�.<br>
	<fmt:setTimeZone value="GMT-8" />
	���� ��¥�� <fmt:formatDate value="${DAY}" type="time" />�Դϴ�.<br>
	���� ��¥�� <fmt:formatDate value="${DAY}" pattern="yyyy�� MM�� dd�� hh�� mm�� ss��" />�Դϴ�.<br>
	</body>
</html>





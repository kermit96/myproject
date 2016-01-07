<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<body>
<%--
	다시 상세보기로 보낸다.
 --%>
		<c:redirect url="../ReBoard/BoardView.reb">
<%--
	jstl을 이용해서 파라메터를 보낼 경우에는 
	<c:param name="키값" value="보낼데이터" />
 --%>
			<c:param name="oriNO" value="${ORINO}" />
			<c:param name="nowPage" value="${NOWPAGE}" />
		</c:redirect>
	</body>
</html>

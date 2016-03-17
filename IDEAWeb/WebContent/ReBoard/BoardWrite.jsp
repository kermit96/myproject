<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script>
			//	자바 스크립트는 함수에 있지 않으면 실행 순서대로 실행된다.
			alert("새로운 글이 등록되었습니다.");
		</script>
	</head>
	<body>
<%--	글쓰기 다음에는 다시 목록 보기를 보여주어야 한다. --%>
		<c:redirect url="../ReBoard/BoardList.reb" />
	</body>
</html>











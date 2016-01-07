<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
		<title>Insert title here</title>
		<script>
			function List() {
				location.href = "../ReBoard/BoardList.reb?nowPage=${NOWPAGE}";
				//	JSTL은 이처럼 자바 스크립트 안에서도 사용할 수 있다.
			}
			function Reple() {
				//	GET 방식 요청
				location.href = "../ReBoard/BoardReWriteForm.reb?oriNO=${DATA.no}"
				//	데이터 빈에있는 데이터를 사용하는 경우는
				//	get 함수의 이름을 이용하면 되는데.....
				//	첫글자는 반드시 소문자로 써야 한다.
				//	나머지 글자는 똑같이 사용해야 한다.
			}
			function Good() {
				location.href = "../ReBoard/BoardGood.reb?oriNO=${DATA.no}&nowPage=${NOWPAGE}";
			}
		</script>
	</head>
	<body>
		<table width="50%" border="1" align="center">
			<tr>
				<td>번호</td>
				<td>${DATA.no}</td>
				<td>조회수</td>
				<td>${DATA.hit}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${DATA.writer}</td>
				<td>날짜</td>
				<td>${DATA.date}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${DATA.title}</td>
			</tr>
			<tr>
				<td>본문</td>
				<td colspan="3">${DATA.brbody}</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<button id="ok" onClick="JavaScript:Good()"><li class="fa fa-thumbs-o-up" style="color:red"> ( ${DATA.ok} )</button>
					<input type="button" id="bed" value="나빠요( ${DATA.bed} )">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a href="JavaScript:List()">목록보기</a>
					<a href="JavaScript:Reple()">답글달기</a>
					<a href="#">수정하기</a>
					<a href="#">삭제하기</a>
				</td>
			</tr>
		</table>
	</body>
</html>

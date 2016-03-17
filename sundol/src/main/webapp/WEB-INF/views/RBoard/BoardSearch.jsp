<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			function goDetail(orino) {
			//	매개변수	선택한 글의 번호가 기억될 예정이다.
				$(location).attr("href", "../RBoard/BoardHit.dol?nowPage=${PINFO.nowPage}&oriNo=" + orino + "&flag=S");
			}
		</script>		
	</head>
	<body>
<!-- 	검색 폼 -->
<!-- 	목록 출력 -->
		<table border="1" align="center" width="80%">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		<c:if test="${empty LIST}">
			<tr>
				<td colspan="5" align="center">
					검색 결과가 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty LIST}">
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.no}</td>
					<td>
						<a href="JavaScript:goDetail(${temp.no})">${temp.title}</a>
					</td>
					<td>${temp.nick}</td>
					<td>${temp.datetime}</td>
					<td>${temp.hit}</td>
				</tr>
			</c:forEach>
		</c:if>
		</table>
<!-- 	페이지 이동 기능 -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
				<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
					<a href="../RBoard/BoardSearch.dol?nowPage=1">[처  음]</a>
					<c:if test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../RBoard/BoardSearch.dol?nowPage=${PINFO.startPage - 1}">[이 전]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="../RBoard/BoardSearch.dol?nowPage=${temp}">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../RBoard/BoardSearch.dol?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
					<a href="../RBoard/BoardSearch.dol?nowPage=${PINFO.totalPage}">[마지막]</a>
				</td>
			</tr>
		</table>
<!-- 	기타 부가기능 -->
	</body>
</html>


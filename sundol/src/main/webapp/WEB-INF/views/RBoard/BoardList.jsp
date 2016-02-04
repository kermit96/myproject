<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			//	상세보기 요청을 해줄 함수
			function goDetail(orino) {
				//	매개변수	선택한 글의 번호가 기억될 예정이다.
				$(location).attr("href", "../RBoard/BoardHit.dol?nowPage=${PINFO.nowPage}&oriNo=" + orino + "&flag=L");
			}
			$(document).ready(function(){
				$("#sBtn").click(function() {
					//	검색단어가 입력되었는지 무결성 검사하고....
					$("#sfrm").attr("action", "../RBoard/BoardSearch.dol");
					$("#sfrm").submit();
				});
				$("#wBtn").click(function(){
					//	글쓰기 폼 요청을 한다.(GET방식)
					$(location).attr("href", "../RBoard/BoardWriteForm.dol");
				});
			});
		</script>
	</head>
	<body>
<!-- 	검색기능 -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<form method="POST" id="sfrm">
						<select id="kind" name="kind">
							<option value="title">제목</option>
							<option value="body">본문</option>
							<option value="writer">글쓴이</option>
							<option value="both">제목 + 본문</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="검색" id="sBtn">
					</form>
				</td>
			</tr>
		</table>
<!-- 	목록 -->
		<table border="1" align="center" width="80%">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.no}</td>
					<td>
<%--	파라메터가 숫자이면 상관없지만....
		파라메터가 문자이면 반드시 ' ' 혹은 " "를 해주어야 한다.
 --%>
						<a href="JavaScript:goDetail(${temp.no})">${temp.title}</a>
					</td>
					<td>${temp.nick}</td>
					<td>${temp.datetime}</td>
					<td>${temp.hit}</td>
				</tr>
			</c:forEach>
		</table>
<!-- 	페이지 이동 -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
				<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
					<a href="../RBoard/BoardList.dol?nowPage=1">[처  음]</a>
					<c:if test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../RBoard/BoardList.dol?nowPage=${PINFO.startPage - 1}">[이 전]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="../RBoard/BoardList.dol?nowPage=${temp}">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../RBoard/BoardList.dol?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
					<a href="../RBoard/BoardList.dol?nowPage=${PINFO.totalPage}">[마지막]</a>
				</td>
			</tr>
		</table>
<!-- 	기타 -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<input type="button" value="글쓰기" id="wBtn">
				</td>
			</tr>
		</table>
	</body>
</html>

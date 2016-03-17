<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script src="../js/jquery-2.1.4.min.js"></script>
		<script>
			$(document).ready(function() {
				$("#sBtn").click(function(){
					//	무결성 검사하고
					$con = $("#content").val();
					if($con == "") {
						alert("검색어를 입력해 주시오");
						return;
					}
					$("#frm").attr("action", "../FileBoard/FileBoardSearch.fbd");
					$("#frm").submit();
				});
				//	단추를 누르면 뭔가를 해야 하므로 단추에 이벤트를 설치하자.
				$("#wBtn").click(function(){
					//	location.href = "";
					$(location).attr("href", "../FileBoard/FileBoardUploadForm.fbd");
					//	참고	attr()	는 속성을 변경하는 기능을 가진 함수이다.
					//	형식>		요소.attr("바꿀속성", "값");
					//	예>			$(img).attr("src", "Girls.gif");
				});
			});
		</script>
	</head>
	<body>
<%--	검색 폼 --%>	
		<form method="POST" name="frm" id="frm">
			<table border="1" align="center" width="80%">
				<tr>
					<td align="center">
						<select name="kind" id="kind">
							<option value="fb_Title">제목</option>
							<option value="fb_Body">본문</option>
							<option value="fb_Writer">글쓴이</option>
							<option value="all">제목 + 본문</option>
						</select>
						<input type="text" name="content" id="content">
						<input type="button" id="sBtn" value="검색하기">
					</td>
				</tr>
			</table>
		</form>
<%--	목록보기 --%>
		<table border="1" align="center" width="80%">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>파일크기</th>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.no}</td>
					<td>${temp.title}</td>
					<td>${temp.writer}</td>
					<td align="right"><fmt:formatNumber value="${temp.len}" pattern="#,###" /> bytes</td>
				<tr>
			</c:forEach>
		</table>
<%--	페이지 나눔 기능 --%>
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
<%--	[이전] [1][2][3][4][5] [다음] --%>				
					<c:if test="${PINFO.startPage eq 1}">
						[이전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="#">[이전]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="#">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다음]
					</c:if>				
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="#">[다음]</a>
					</c:if>				
				</td>
			</tr>
		</table>
<%--	부가기능 --%>
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<input type="button" value="글쓰기" id="wBtn">
				</td>
			</tr>
		</table>
	</body>
</html>





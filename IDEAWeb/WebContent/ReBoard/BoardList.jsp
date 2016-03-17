<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	<Script>
		function BoardWrite() {
			//	자바스크립트를 이용해서 요청하는 방법
			//		1.	GET
			//		location.href = "요청내용";
			location.href = "../ReBoard/BoardWriteForm.reb";
			//		2.	POST
			//		폼.submit();
		}
	</Script>
	</head>
	<body>
<%--	게시판 목록 보여주고 --%>
		<table width="70%" border="1" align="center">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			<%-- 이제 게시물의 개수만큼 반복해서 보여준다.--%>
			<c:forEach var="temp" items="${LIST}">
				<%--	temp 변수에는 LIST에 있는 내용 한개씩 들어오는데....
						LIST에 있는 내용은 데이터 빈 클래스이다.
				 --%>
				 <tr>
				 	<td>${temp.no}</td>
				 	<td>
<%--	원글이 아니면..... 들여쓰기 해서 보여주는 것이 이쁘다. --%>
<c:if test="${temp.step ne 0}">
	<c:forEach var="test" begin="1" end="${temp.step}">
		&nbsp;&nbsp;
	</c:forEach>
		=>
</c:if>

				 		<a href="../ReBoard/BoardView.reb?oriNO=${temp.no}&nowPage=${PINFO.nowPage}">${temp.title}</a>
				 	</td>
				 	<td>${temp.writer}</td>
				 	<td>${temp.date}</td>
				 	<td>${temp.hit}</td>
				 </tr>
			</c:forEach>
		</table>
<%--	페이지 이동 기능 
		[이전]	[1][2][3][4][5] [다음]
--%>
		<table width="70%" border="1" align="center">
			<tr>
				<td align="center">
					<c:if test="${PINFO.startPage eq 1}">
						[이전]
					</c:if>				
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../ReBoard/BoardList.reb?nowPage=${PINFO.startPage - 1}">[이전]</a>
					</c:if>
					<c:forEach var="imsi" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<c:if test="${imsi eq PINFO.nowPage}">
							[${imsi}]
						</c:if>
						<c:if test="${imsi ne PINFO.nowPage}">
							<a href="../ReBoard/BoardList.reb?nowPage=${imsi}">[${imsi}]</a>
						</c:if>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../ReBoard/BoardList.reb?nowPage=${PINFO.endPage + 1}">[다음]</a>
					</c:if>
				</td>
			</tr>
		</table>
<%--	목록보기에서 사용할 부가적인 기능 --%>
		<table width="70%" border="1" align="center">
			<tr>
				<td align="center">
<%--	로그인을 하지 않은 사람에게는 글쓰기 단추를 보여주지 말자 --%>
					<c:if test="${sessionScope.ID ne null}">
						<a href="JavaScript:BoardWrite()">글쓰기</a>
					</c:if>
				</td>
			</tr>		
		</table>
	</body>
</html>
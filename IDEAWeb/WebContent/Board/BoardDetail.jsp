<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script>
			function	ShowList() {
				//	필요한 작업을 하고 나서 목록보기로 요청하고 싶다.
				alert("나 실행되니");
				location.href = "BoardList.bbs";
				//	<a href>와 동일한 효과이다.
			}
			
			function Reply() {
				//	할일
				//		서버에게 보내기 전에 무결성 검사를 하고자 한다.
				//		즉, 본문을 입력하지 않았으면 댓글이 없는 상태이므로 서버에 데이터 보낼 필요가 없다.
				//	1.	textarea에 입력한 값을 알아낸다.
				var	area = document.getElementById("rebody").value;
				//		입력 폼의 내용을 알아내는 속성				value
				//		일반 HTML 요소의 내용을 알아내는 속성	innerHTML
				if(area == "") {
					alert("댓글을 입력해 주세요");
					return;
				}
				//	2.	그러면 폼의 내용을 서버에 보내자.
				var	frm = document.getElementById("refrm");
				frm.submit();
			}
		</script>
	</head>
	<body>
<%--
	선택한 게시물의 상세보기 내용을 출력하고
 --%>
		<table width="50%" border="1" align="center">
			<tr>
				<td>글번호</td>
				<td>${DATA.NO}</td>
				<td>조회수</td>
				<td>${DATA.HIT}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${DATA.WRITER}</td>
				<td>작성일</td>
				<td>${DATA.DATE}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${DATA.TITLE}</td>
			</tr>
			<tr>
				<td>본문</td>
				<td colspan="3">${DATA.CONTENT}</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<%--필요한 기능 예>	목록보기, 수정하기,......--%>
					<a href="JavaScript:ShowList()">목록보기</a>
			<c:if test="${sessionScope.ID eq DATA.WRITER}">
<%--	sessionScope.ID		세션이 가지고 있는 데이터 중 ID 값 
		sessionScope는		JSTL이 사용하는 세션 정보를 의미하는 예약어
--%>
					<a href="../Board/BoardModifyForm.bbs?oriNO=${DATA.NO}">수정하기</a>
					<a href="">삭제하기</a>
			</c:if>
<%--
	참고	요청을 지정하는 방법
			1.	절대 요청으로 처리하는 방법
				http://localhost:8080/IDEAWeb/Board/BoardDetail.bbs		방식
			
			2.	상대 요청으로 처리하는 방법
				현재 상태를 기준으로 해서 달라진 부분만 명시하는 방법
				예>	현재 상태		http://localhost:8080/IDEAWeb/Board/BoardDetail.bbs
						요청 내용		http://localhost:8080/IDEAWeb/Board/BoardList.bbs
						
				<a href="BoardList.bbs">	
			
				예>	현재 상태		http://localhost:8080/IDEAWeb/Board/BoardDetail.bbs
						요청 내용		http://localhost:8080/IDEAWeb/Member/LoginProc.bbs
			
				<a href="../Member/LoginProc.bbs">
				
				
				실무에서는 상대 요청을 훨씬더 많이 사용한다.
				왜냐하면 절대주소는 주소가 변경되면 모든 요청을 다시 수정해야 하기 때문이다.
 --%>
				</td>
			</tr>
		</table>
<%--
	그 게시물에 쓰여진 댓글을 보여주고
--%>
		<c:forEach var="temp" items="${LIST}">
			<table width="50%" border="1" align="center">
			<tr>
				<td>댓글번호</td>
				<td>${temp.NO}</td>
				<td>작성일</td>
				<td>${temp.DATE}</td>
			</tr>		
			<tr>
				<td>글쓴이</td>
				<td colspan="3">${temp.WRITER}</td>
			</tr>
			<tr>
				<td>본문</td>
				<td colspan="3">${temp.BODY}</td>
			</tr>
		</table>
		</c:forEach>
<%--
	댓글쓰기 폼 보여주고
	댓글쓰기를 하기 위해서는 서버에게 
		1.	본문
		2.	원글의 번호			를 보내주어야 한다.
		
	원글의 번호는 어떻게 알려줄 것인가?
	★★★
	문제	POST 방식 즉 form에 의해서 서버에 내용을 보내는 경우(submit)에는 form에 있는 것만 서버에 보낼 수 있다.
			form 밖에 있는 내용은 서버에 보낼 수 없다.
	결론	만약 어떤 데이터를 반드시 서버에 보내야 한다면 그 데이터는 반드시 form 안쪽에 존재하고 있어야 한다.			

	결론	만약 어떤 데이터를 반드시 서버에 보내야 한다면 근데 그 데이터를 클라이언트에게 노출하고 싶지 않다면....
			<input type="hidden" name="" value="">
			로 작업해 놓아야 한다.
			
			이렇게 해 놓으면 서버에 value에 지정한 값이 전달된다.			 		
--%>
		<form method="POST" action="../Board/BoardReWrite.bbs" id="refrm">
			<input type="hidden" name="oriNO" value="${DATA.NO}">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="writer" value="${sessionScope.ID}" disabled></td>
				</tr>		
				<tr>
					<td>본문</td>
					<td><textarea name="body" id="rebody" cols="50" rows="5"></textarea></td>
				</tr>		
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="댓글쓰기" onClick="JavaScript:Reply()">
					</td>
				</tr>		
			</table>
		</form>
	</body>
</html>





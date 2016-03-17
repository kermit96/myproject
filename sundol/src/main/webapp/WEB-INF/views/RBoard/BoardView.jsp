<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			//	Jquery의 단점은
			//	이미 완성된 폼에 대해서 작업을 할 수 있다.
			//	즉, 정해진 id를 이용해서 작업하는 것을 원칙으로하고 있으므로..
			//	단점	동적인 폼에 대해서는 매우 불편하다.
			//	해결방법	일반 자바스크립트로 함수를 만들고
			//				그 안에서 Jquery를 이용해야 한다.
			function deleteReple(no) {
				//	GET -> POST로 돌리기 위해서 폼을 이용했다.
				//	폼안에 있는 데이터만 서버로 간다.
				$pw = $("#pws" + no).val();
				//	무결성검사하고
				$("#pw3").val($pw);
				$("#reNo").val(no);
				$("#mfrm").attr("action", "../RBoard/BoardRepleDelete.dol");
				$("#mfrm").submit();
			}
			function reGood(no) {
				$.ajax({
					url : "../RBoard/BoardRepleGood.dol",
					data : "reNo=" + no + "&temp=" + new Date(),
					dataType : "xml",
					type : "GET",
					success : function(data) {
						//	할일
						//		넘어온 데이터 받는다.
						$cnt = $(data).find("good").text();
						//		이것을 좋아요 단추에 출력한다.
						$("#good" + no).val("좋아요 ("+$cnt+")");
					},
					error : function() {
						alert("에러다");
					}
				});
			}			
			$(document).ready(function(){
				$("#lBtn").click(function(){
					$(location).attr("href", "../RBoard/BoardList.dol?nowPage=${NOWPAGE}");
				});
				$("#sBtn").click(function(){
					$(location).attr("href", "../RBoard/BoardSearch.dol?nowPage=${NOWPAGE}");
				});
				$("#gBtn").click(function(){
					$.ajax({
						url : "../RBoard/BoardGood.dol",
						data : "oriNo=${DATA.no}&temp=" + new Date(),
						dataType : "xml",
						type : "GET",
						success : function(data) {
							//	응답 결과를 좋아요 단추에 기록해준다.
							$good = $(data).find("good").text();
							$("#gBtn").val("좋아요 (" + $good + ")");
						},
						error : function() {
							alert("에러다");
						}
					});
				});
				$("#dBtn").click(function() {
					//	같은 기법으로 비번을 서버에 알려주어야 하므로
					//	POST 방식으로 처리하도록 한다.
					//	우리는 이미 임시폼을 만들어 놓았으므로 그것을 이용하도록 한다.
					$pw = $("#pw1").val();
					//	무결성 검사하고
					//	임시 폼에 있는 pw저장하는 곳에 옮긴다.
					$("#pw3").val($pw);
					//	서버에 요청한다.
					$("#mfrm").attr("action", "../RBoard/BoardDelete.dol");
					$("#mfrm").submit();
				});
				$("#mBtn").click(function(){
					//	원래 수정하기 단추는 폼이 아니므로 GET 방식으로 요청을 해야 한다.
					//	이때는 정보가 노출되는 위험이 있다.
					//	강제로 POST 방식으로 처리하는 편법이 있다.
					//	원리	임시로 폼을 만들고
					//			그 폼은 보여주면 안되므로 모든 데이터를 hidden으로 가지고 있도록 한다.
					//			그폼을 이용해서 서버에 전송하는 방법
					
					//	할일
					//		비밀번호를 입력받는다.
					$pw = window.prompt("비밀번호를 입력해주세요.");					
					//		입력된 결과를 임시 폼에 데이터로 강제로 써준다.
					$("#pw3").val($pw);
					//		서버에 보낸다.
					$("#mfrm").attr("action", "../RBoard/BoardModifyForm.dol");
					$("#mfrm").submit();
				});
				$("#rBtn").click(function(){
					//	무결성 검사 하시고
					$pw = $("#pw2").val();
					//	......
					$("#rfrm").attr("action", "../RBoard/BoardRepleWrite.dol");
					$("#rfrm").submit();
				});
			});
		</script>
	</head>
	<body>
<!-- 	
		수정하기 폼 요청에 필요한 데이터를 POST 방식으로 보내기 위한 임시 폼이다. 
		이 안에 요청에 필요한 모든 데이터를 hidden으로 만들어 놓으면 된다.
-->
		<form method="POST" id="mfrm">
			<input type="hidden" id="pw3" name="pw" value="">
			<input type="hidden" id="reNo" name="rno" value="">
			<input type="hidden" id="oriNo" name="oriNo" value="${DATA.no}">
			<input type="hidden" id="nowPage" name="nowPage" value="${NOWPAGE}">
		</form>
		<table width="50%" border="1" align="center">
			<tr>
				<td>글번호</td>
				<td>${DATA.no}</td>
				<td>조회수</td>
				<td>${DATA.hit}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${DATA.nick}</td>
				<td>작성일</td>
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
<c:if test="${sessionScope.ID eq DATA.id}">
			<tr>
				<td>비밀번호</td>
				<td colspan="3">
					<input type="password" name="pw" id="pw1">
					<input type="button" value="삭제하기" id="dBtn">
					삭제를 원하시면 비밀번호를 입력해주시오
				</td>
			</tr>
</c:if>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="좋아요 (${DATA.good})" id="gBtn">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
<c:if test="${KIND eq 'L'}">
					<input type="button" value="목록보기" id="lBtn">
</c:if>
<c:if test="${KIND eq 'S'}">
					<input type="button" value="목록보기" id="sBtn">
</c:if>
<c:if test="${sessionScope.ID eq DATA.id}">
					<input type="button" value="수정하기" id="mBtn">
</c:if>					
				</td>
			</tr>
		</table>
<!-- 	댓글 보기 -->
		<c:forEach var="temp" items="${RDATA}">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글번호</td>
					<td>${temp.rno}</td>
					<td>작성일</td>
					<td>${temp.date}</td>
				</tr>
				<tr>
					<td>글쓴이</td>
					<td colspan="3">${temp.nick}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3">${temp.brbody}</td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<input type="button" id="good${temp.rno}" value="좋아요 (${temp.good})" onClick="JavaScript:reGood(${temp.rno})">
					</td>
				</tr>
				<tr>
					<td colspan="4">
<!-- 	이제 폼에 id값을 정해야 하는데 
		id는 그 jsp 문서 안에서는 유니끄 해야한다.
		어떻게 해서던지 유니끄하게 id를 배정할 방법을 찾아야 한다.
		
		그 유니끄한 규칙을 이용할 수 있도록 처리해 주어야 한다.
		우리로 말하면 지금 단추가 3개 있는데 어떤 단추를 눌렀는지를 자바스크립트 함수가
		찾아낼 수 있도록 하려면....
		단추의 id		drBtn5,	 drBtn10, drBtn14
		
		결론		숫자를 알려주면 앞의 drBtn은 고정되었으므로 찾을 수 있지 않을까?
-->
						<input type="password" id="pws${temp.rno}">
						<input type="button" id="drBtn${temp.rno}" value="삭제" onClick="JavaScript:deleteReple(${temp.rno})">
						비밀번호를 입력한 후 삭제하세요
					</td>
				</tr>
			</table>
		</c:forEach>
<!-- 	댓글 쓰기 -->
		<form method="POST" id="rfrm">
			<!-- 	
					댓글을 쓰기 위해서는 원글의 번호를 알려주어야 한다. 
					하지만 유저에게 보이기는 싫다.
			-->
			<input type="hidden" value="${DATA.no}" name="no">
			<input type="hidden" value="${NOWPAGE}" name="nowPage">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" value="${sessionScope.NICK}" disabled></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea id="body" name="body"></textarea></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="pw2" name="pw"></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="button" id="rBtn" value="댓글쓰기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
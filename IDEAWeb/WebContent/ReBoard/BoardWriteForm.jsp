<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script>
			function Write() {
				//	할일
				//		제목과 본문이 입력 되었는지 확인하고
				var	title = document.getElementById("title").value;
				if(title == "") {
					alert("제목을 입력해 주세요");
					return;
				}
				var	body = document.getElementById("body").value;
				if(body == "") {
					alert("본문을 입력해 주세요");
					return;
				}
				//		제대로 입력이 되었으면 서버에 보내준다.
				var	frm = document.getElementById("frm");
				//	frm.속성 = "내용";
				//	해당 속성을 변경하는 기능
				frm.action = "../ReBoard/BoardWrite.reb";
				//	이 부분은 action 이라는 속성 값을 변경한다.
				frm.submit();
			}
			function Reset() {
				//	폼전체에 입력한 내용을 초기화 시키는 방법
				//	형식>		폼.reset();
				var	frm = document.getElementById("frm");
				frm.reset();
			}
			function List() {
				//	GET 방식을 이용해서 목록 보기를 요청
				location.href = "../ReBoard/BoardList.reb";
			}
		</script>
	</head>
	<body>
<%--
		글쓰기 폼을 보여주면 된다.
		하지만 역시 뷰도 주소창에서 직접 들어오는 경우가 있다.
 --%>
 	<c:if test="${sessionScope.ID eq null}">
 		<c:redirect url="../Member/LoginForm.jsp" />
 	</c:if>
<%--	자바스크립트와 jquery를 이용해서 뭔가 작업을 하고 싶을때는
		주로 id 값을 이용해서 특정 요소를 선택한다.
		서버는 주로 name 값을 이용해서 값을 처리한다.
		결론	폼을 만들때는 습관적으로 id, name 값을 둘 다 제공하도록 한다.
 --%>	
 	<form method="POST" id="frm" name="frm" action="">
	<table width="50%" border="1" align="center">
		<tr>
			<td>글쓴이</td>
			<td><input type="text" disabled value="${sessionScope.ID}"></td>
		</tr>	
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" id="title"></td>
		</tr>	
		<tr>
			<td>본문</td>
			<td><textarea name="body" id="body"></textarea></td>
		</tr>	
		<tr>
			<td colspan="2" align="center">
				<a href="JavaScript:Write()">글쓰기</a>
				<a href="JavaScript:Reset()">다시쓰기</a>
				<a href="JavaScript:List()">목록보기</a>
			</td>
		</tr>	
	</table>
	</form>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=utf-8"" pageEncoding="utf-8""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"">
		<title>Insert title here</title>
		<script>
			function SendData() {
				//	무결성 검사하자.
				var	title = document.getElementById("title").value;
				if(title == "") {
					alert("제목을 입력해라");
					return;
				}
				var	body = document.getElementById("body").value;
				if(body == "") {
					alert("본문좀 입력해라");
					return;
				}
				var	frm = document.getElementById("frm");
				frm.action = "../ReBoard/BoardReWrite.reb";
				frm.submit();
			}
		</script>
	</head>
	<body>
		<form method="POST" id="frm" name="frm">
<%--
	폼에 있는 내용을 서버에게 보내는 submit() 작업은 폼에 있는 데이터만 서버에게 넘어간다.
	우리는 서버에게 이 글이 어떤글에 대한 답글인지를 알려주어야 하는데....
	원본글의 글 번호는 현재는 폼안에 있지 않으므로 서버에게 알려줄 없다.
	이처럼 서버에게 주어야 하는 데이터인데, 화면에는 굳이 출력할 필요가 없는 데이터가 있다.
	이런 데이터는 숨겨서 만들어 놓음으로써 서버에게 전달되도록 해야 한다 
--%>
			<input type="hidden" name="oriNO" value="${ORINO}">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" disabled value="${sessionScope.ID}">
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title">
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="body" id="body"></textarea>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="JavaScript:SendData()">글쓰기</a>
						<a href="#">다시쓰기</a>
						<a href="#">원글보기</a>
						<a href="#">목록보기</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#mBtn").click(function(){
					//	할일
					//	무결성 검사하고
					
					//	서버에게 요청한다.
					$("#mfrm").attr("action", "../RBoard/BoardModify.dol");
					$("#mfrm").submit();
				});
			});
		</script>
	</head>
	<body>
		<form method="POST" id="mfrm">
			<input type="hidden" name="no" value="${DATA.no}">
			<input type="hidden" name="nowPage" value="${NOWPAGE}">
			<table border="1" align="center" width="50%">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" value="${DATA.nick}" disabled></td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" id="title" name="title" value="${DATA.title}">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea id="body" name="body">${DATA.body}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="mBtn" value="수정하기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

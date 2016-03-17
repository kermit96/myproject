<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					//	무결성 검사를 반드시 진행하시고
					//	즉, 반드시 존재해야 하는 데이터나? 특정 형식을 가진
					//	데이터가 그 형식을 만족하는지를 검사하는 것
					
					//	참고
					//		html5에서는 무결성 검사를 하는 속성이 존재하고 있다.
					//		이것을 이용해서 처리해도 무방하다.
					$("#wfrm").attr("action", "../RBoard/BoardWrite.dol");
					$("#wfrm").submit();
				});
			});
		</script>
	</head>
	<body>
<%--	
		폼안에는 글쓴이를 입력할 수 있는 장소
		제목, 본문, 비밀번호를 입력할 수 있는 장소를 만들어주면 된다.
 --%>
		<form method="POST" id="wfrm">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" value="${sessionScope.NICK}" disabled></td>
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
					<td>비밀번호</td>
					<td><input type="password" id="pw" name="pw"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="글쓰기" id="wBtn">
					</td>
				</tr>
			</table>		
		</form>
	</body>
</html>
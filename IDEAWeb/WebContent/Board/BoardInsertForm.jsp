<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%--	게시물을 등록할 수 있는 글쓰기 폼을 만들자. --%>
		<form method="POST" action="../Board/BoardInsert.bbs">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td>
						<input type="text" name="writer" disabled value="<%= session.getAttribute("ID") %>">
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="title">
					</td>
				</tr>
				<tr>
					<td>본문</td>
					<td>
						<textarea name="body" cols="50" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기">
					</td>
				</tr>
			</table>		
		</form>
	</body>
</html>
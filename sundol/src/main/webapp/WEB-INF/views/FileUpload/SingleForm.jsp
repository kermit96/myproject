<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#uBtn").click(function(){
					//	무결성 검사하고
					//	전송한다.
					$("#ufrm").submit();
				});
			});
		</script>
	</head>
	<body>
		<form method="POST" id="ufrm" action="../FileUpload/SingleUpload.dol" enctype="multipart/form-data">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="writer" id="writer"></td>
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
					<td>첨부파일</td>
					<td><input type="file" name="upfile" id="upfile"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="올리기" id="uBtn">
					</td>
				</tr>
			</table>		
		</form>
	</body>
</html>
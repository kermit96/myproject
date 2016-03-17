<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="../se2/js/HuskyEZCreator.js" charset="utf-8">
		</script>
	</head>
	<body>
		<form method="POST" id="frm" action="">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="writer" id="writer">
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title">
				</tr>
				<tr>
					<td colspan="2">
						<textarea id="body" name="body" cols="60" rows="20"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="글쓰기" id="wBtn">
					</td>
				</tr>
			</table>		
		</form>
	</body>
<!-- 	body라는 textarea가 만들어지기 전에 이 스크립트가 실행되면
		스킨이 입혀지지 않을 수 있다.
 -->
	<script>
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "body",
			sSkinURI: "../se2/SmartEditor2Skin.html",
			fCreator: "createSEditor2"
		});
	</script>
</html>

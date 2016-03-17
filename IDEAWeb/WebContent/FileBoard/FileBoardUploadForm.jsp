<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script src="../js/jquery-2.1.4.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#rBtn").click(function(){
					//	자바스크립트		폼요소.reset();
					
					//	jquery에서는 한번에 폼의 내용을 다 지우지 못한다.
					//	폼안에 있는 내용을 하나씩 찾아서 지워야 한다.
					$("#frm").each(function(){
						//	이렇게 하면 폼 안에 있는 내용이 하나씩 this라는
						//	예약 변수에 기억이 되면서 반복한다.
						this.reset();
					});
				});
				$("#wBtn").click(function() {
					$("#frm").attr("action", "../FileBoard/FileBoardUpload.fbd");
					$("#frm").submit();
				});
			});
		</script>
	</head>
	<body>
<%--
	지금부터 파일 업로드가 가능한 폼을 제작한다.
	파일 업로드가 가능한 폼은 일반 폼과 별반 차이는 없으나......
	차이점
			★★★
	1.		<form에서 반드시 enctype="multipart/form-data" 로 지정해야 한다.
			이렇게 지정하지 않으면(지금까지 방식)
			parameter 전송이라고 해서 폼안에 있는 데이터만 서버에 전송되는 방식이된다.
			파일의 내용은 서버에 가지 못한다.
			
			enctype을 지정하면
			스트림 전송 방식이라고 하여
			폼안에 있는 데이터가 약속된 규칙에 의해서 스트림으로 변환되어서 전송되는 방식이 된다.
			즉, 폼에 입력한 내용이 아니고 그것을 스트림으로 바꾸기 때문에
			폼에 입력한 내용이 만약 파일이면 그 파일의 내용도 같이 서버에 가게 된다.		
			
			단점		request.getParameter("")로 데이터를 받지 못하고
						스트림을 분석해서 데이터를 골라내야 한다.	
			
	2.	업로드할 파일은	<input type="file">으로 만들어서 처리해야 한다.			
 --%>	
		<form method="POST" id="frm" name="frm" enctype="multipart/form-data">
			<table width="50%" align="Center" border="1">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="writer" id="writer" value="${sessionScope.ID}" disabled></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="body" id="body"></textarea>
				</tr>
				<tr>
					<td>업로드파일</td>
					<td><input type="file" id="upfile" name="upfile" value="파일선택">
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="wBtn" value="글쓰기">
						<input type="button" id="rBtn" value="다시쓰기">
						<input type="button" id="lBtn" value="목록보기">
					</td>
				</tr>
			</table>		
		</form>
	</body>
</html>
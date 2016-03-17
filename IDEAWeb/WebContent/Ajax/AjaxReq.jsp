<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function AjaxSend() {
				//	이 함수가 실행되면(단추를 누르면...)
				//	ajax를 통해서	AjaxResp.jsp를 서버에 요청해서 달라고 한다.
				$.ajax({
					url:"AjaxResp.jsp",
					type:"GET",
					dataType:"html",
					success: function(data){
						$("#div1").html(data);
					},
					error: function(){
						alert("이거나오면 안되는데....");
					}
				});
				//	이제 필요한 속성을 쓰면 된다.
				//	속성의 순서는 관계가 없다.
				//	필요한 속성만 골라서 쓰면 된다.
			}
		</script>
	</head>
	<body>
		<table width="80%" border="1" align="center">
			<tr height="150">
				<td>이 부분은 변함이 없다.</td>
			</tr>		
			<tr height="300">
				<td>
					<div id="div1">이 부분이 변할 것이다.</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" value="눌러" onClick="JavaScript:AjaxSend()">
				</td>
			</tr>
			<tr height="150">
				<td>이 부분은 변함이 없다.</td>
			</tr>		
		</table>
	</body>
</html>
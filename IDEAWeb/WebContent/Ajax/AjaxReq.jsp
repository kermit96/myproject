<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function AjaxSend() {
				//	�� �Լ��� ����Ǹ�(���߸� ������...)
				//	ajax�� ���ؼ�	AjaxResp.jsp�� ������ ��û�ؼ� �޶�� �Ѵ�.
				$.ajax({
					url:"AjaxResp.jsp",
					type:"GET",
					dataType:"html",
					success: function(data){
						$("#div1").html(data);
					},
					error: function(){
						alert("�̰ų����� �ȵǴµ�....");
					}
				});
				//	���� �ʿ��� �Ӽ��� ���� �ȴ�.
				//	�Ӽ��� ������ ���谡 ����.
				//	�ʿ��� �Ӽ��� ��� ���� �ȴ�.
			}
		</script>
	</head>
	<body>
		<table width="80%" border="1" align="center">
			<tr height="150">
				<td>�� �κ��� ������ ����.</td>
			</tr>		
			<tr height="300">
				<td>
					<div id="div1">�� �κ��� ���� ���̴�.</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" value="����" onClick="JavaScript:AjaxSend()">
				</td>
			</tr>
			<tr height="150">
				<td>�� �κ��� ������ ����.</td>
			</tr>		
		</table>
	</body>
</html>
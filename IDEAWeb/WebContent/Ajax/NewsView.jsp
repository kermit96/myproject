<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			var	num = 0;		//	������� ���� ��ȣ�� ����� ����
			setInterval(SendAjax, 3000);			
			//	Ư���ð����� �ڵ� ȣ���� �Լ�
			function	SendAjax() {
				//	ȣ��Ǵ� ���� AJAX ����� ���ؼ� �������� ������
				//	�����޶�� ��û�� �����̴�.
				num = num + 1;
				if(num > 5) {
					num = 1;
				}				
				//	������� ���� ��ȣ�� ���ߴ�.
				$.ajax({
					url : "NewsResp.jsp",
					type: "GET",
					dataType: "html",
					data:"no=" + num,		//	�Ķ���͸� ������ �Ӽ�
					//	�Ķ���ʹ�	Ű��=������&Ű��=������  �� ������� ������ �ȴ�. 
					success:function(data){
						var	ddd = document.getElementById("news");
						ddd.innerHTML = data;
					},
					error:function(){
						alert("������");
					}
				});
			}
			
		</script>
	</head>
	<body>
		<div id="news">�� �κ��� ������ ���� �κ��̴�.</div>
		������ �κп��� ����� ���� �ٸ����� ����� ���´�.
	</body>
</html>
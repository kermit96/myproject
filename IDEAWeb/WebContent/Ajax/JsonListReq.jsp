<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function SendAjax() {
				$.ajax({
					url: "JsonListResp.jsp",
					dataType: "json",
					type:"GET",
					success:function(data){
						var	h = "<table width='50%' border='1' align='center'>";
						$.each(data, function(index){
							//	index ���� �迭�� ������ 0���� ���������� 
							//	���Ǹ� �ݺ��ȴ�.
							var	temp = data[index];
							h += "<tr>";
							h += "<td>";
							h += temp;
							h += "</td>";
							h += "</tr>";
						});
						h += "</table>";
						
						var ddd = document.getElementById("div1");
						ddd.innerHTML = h;
					},
					error:function(){
						alert("�� ������ �ȵǿ�");
					}
				});				
			}
		</script>
	</head>
	<body>
		<div id="div1"></div>
		<input type='button' value="����" onClick="JavaScript:SendAjax()">
	</body>
</html>



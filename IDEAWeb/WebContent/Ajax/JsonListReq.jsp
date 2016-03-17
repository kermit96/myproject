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
							//	index 에는 배열의 순서가 0부터 순차적으로 
							//	기억되면 반복된다.
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
						alert("너 나오면 안되요");
					}
				});				
			}
		</script>
	</head>
	<body>
		<div id="div1"></div>
		<input type='button' value="눌러" onClick="JavaScript:SendAjax()">
	</body>
</html>



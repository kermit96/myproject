<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			var	num = 0;		//	보고싶은 뉴스 번호를 기억할 변수
			setInterval(SendAjax, 3000);			
			//	특정시간마다 자동 호출할 함수
			function	SendAjax() {
				//	호출되는 순간 AJAX 기법을 통해서 서버에게 뉴스를
				//	보내달라고 요청할 예정이다.
				num = num + 1;
				if(num > 5) {
					num = 1;
				}				
				//	보고싶은 뉴스 번호를 구했다.
				$.ajax({
					url : "NewsResp.jsp",
					type: "GET",
					dataType: "html",
					data:"no=" + num,		//	파라메터를 보내는 속성
					//	파라메터는	키값=데이터&키값=데이터  의 방식으로 보내면 된다. 
					success:function(data){
						var	ddd = document.getElementById("news");
						ddd.innerHTML = data;
					},
					error:function(){
						alert("에러다");
					}
				});
			}
			
		</script>
	</head>
	<body>
		<div id="news">이 부분이 뉴스가 나올 부분이다.</div>
		나머지 부분에는 만들고 싶은 다른것을 만들어 놓는다.
	</body>
</html>
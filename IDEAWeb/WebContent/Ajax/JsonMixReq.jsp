<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function Send() {
				$.ajax({
					url:"JsonMixResp.jsp",
					dataType:"json",
					type:"GET",
					//	ajax는 한번 요청한 내용과 동일한 요청이 다시 들어오면
					//	서버가 응답하지 않는 경우도 생긴다.
					//	이 문제를 해결하는 방법은
					//	요청을 바꾸는 방법이 있는데....
					//	요청을 바꿀 수 없으므로 파라메터를 바꿔서 요청하면 된다.
					data : "temp=" + new Date(), 					
					//	성공하면 실행될 함수는 따로 만들어서 사용할 수 있다.
					//	단 어떤 함수를 사용할지만 등록해 주면 된다.
					success: ShowData,
					error:function(){
						alert("나오면 안되요");
					}
				});
			}
			//	성공하면 실행될 함수이다.
			function ShowData(data) {
				//	1.	가장 크게는 맵 형식이므러 그 안에 데이터를 꺼낸다.
				var	friends = data.kimfriend;
				//	이것의 결과는 배열이 될 것이다.
				//	이제는 배열 형태로 꺼내야 한다.
				var	h = "<table border='1' align='center' width='70%'>";
				$.each(friends, function(index){
					var	friend = friends[index];
					//	이것은 다시 맵으로 나오게 된다.
					var	imsi = friend.Friend;
					//	이것도 다시 맵이다.
					var	name = imsi.name;
					var	tel = imsi.phone;
					var	addr = imsi.addr;
					var	age = imsi.age;
					h += "<tr>";
					h += "<td>" + name + "</td>";
					h += "<td>" + tel + "</td>";
					h += "<td>" + addr + "</td>";
					h += "<td>" + age + "</td>";
					h += "</tr>";
				});
				h += "</table>";				

				$("#div1").html(h);
			}
		</script>
	</head>
	<body>
		<div id="div1"></div>
		<input type="button" value="눌러" onClick="JavaScript:Send()">
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function ReqAjax() {
				//	이제 이 함수가 실행되는 순간 ajax 기법을 이용해서 서버에 요청을 한다.
				$.ajax({
					url:"JsonMapResp.jsp",
					dataType:"json",
					type:"GET",
					success:function(data) {
						var	h = "<table width='50%' border='1` align='center'>";
						h += "<tr>";
						h += "<td>";
						h += data.이름;
						h += "</td>";
						h += "<td>";
						h += data.나이;
						h += "</td>";
						h += "</tr>";
						h += "<tr>";
						h += "<td>";
						h += data.성별;
						h += "</td>";
						h += "<td>";
						h += data.신장;
						h += "</td>";
						h += "</tr>";
						h += "</table>";
						//	이처럼 Ajax은 받은 결과(데이터)를 이용해서 자바 스크립트로
						//	필요한 폼을 만들어서 사용하는 것이 원칙이다.
						$("#div1").html(h);
					},
					error:function(){
						alert("나오는 안되요");
					}
				});
			}
		</script>
	</head>
	<body>
<%--	응답 받은 결과를 출력할 위치를 잡아주고.... --%>	
		<div id="div1"></div>
		<a href="JavaScript:ReqAjax()">눌러</a>
	</body>
</html>

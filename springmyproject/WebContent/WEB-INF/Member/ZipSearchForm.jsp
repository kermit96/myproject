<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			//	자동적으로 데이터를 입력할 스크립트 함수를 만든다.
			function autoSet(code, addr) {
				//	현재 화면은 회원가입 폼에 의해서 나타난 폼이다.
				//	그러므로 우편번호검색 화면은 회원가입 폼에 의해서 open된 폼이며
				//	우편번호검색 화면 입장에서 회원가입 폼은 opener이다.
				//	특정 화면에서 opener에 있는 요소를 작업하는 방법
				//		문법		자바 스크립트		opener.document.getElementById("id").함수	
				//					jQuery				$("#id", opener.document).함수()
				$("#zipcode", opener.document).val(code);
				//	자바스크립트
				//		opener.document.getElementById("zipcode").value = code;
				$("#address", opener.document).val(addr);
				
				//	이제 우편번호검색 화면은 닫는다.
				self.close();
			}
		
			$(document).ready(function(){
				$("#zBtn").click(function() {
					//	파라메터로 넘길 데이터 알아내기
					$sido = $("#sido").val();
					$gugun = $("#gugun").val();
					$dong = $("#dong").val();
					$road = $("#road").val();
					//	Ajax를 이용해서 요청을 한다.
					
					$.ajax({
						url : "../Member/ZipSearch.do",
						data : "sido=" + encodeURIComponent($sido) + "&gugun=" + encodeURIComponent($gugun) + "&dong=" +
						encodeURIComponent($dong) + "&road=" + encodeURIComponent($road) + "&temp=" + new Date(),
					// 	dataType : "xml",
						dataType : "json",
						type : "GET",
						success : function(data){
						  // $codes = $(data).find("code");
						  						
							var	h = "<table width='95%' border='1' align='center'>"; 
						// 	$codes.each(function() {
							
							$.each(data,function(i,item) {
//								$zip = $(this).find("zip").text();
//								$addr = $(this).find("addr").text();
                               $zip = item.zip;
                               $addr = item.addr;

								h += "<tr>";
								//	원래는 "다음은 ' 다음은 ".....순서로 나가야 한다.
								//	참고로			\"		\'	는 앞뒤에 있는 ", '을 무시하고 그대로 출력하라는 의미
								h += "<td><a href='JavaScript:autoSet(\""+$zip+"\", \""+$addr+"\")'>" + $zip + "</a></td>";
								h += "<td>" + $addr + "</td>";
								h += "</tr>";
							});
							h += "</table>";
							
							$("#zip").html(h);
						},
						error : function() {
							alert("에러다");
						}
					});
				});
			});
			$(document).ready(function() {
				$("#dong").change(function(){
					$sido = $("#sido").val();
					$gugun = $("#gugun").val();
					$dong = $("#dong").val();
					$.ajax({
						url : "../Member/ZipSearchRoad.do",
						data : "sido=" + encodeURIComponent($sido) + "&gugun=" + encodeURIComponent($gugun) + "&dong=" + 
						encodeURIComponent($dong) + "&temp=" + new Date(),
						dataType : "xml",
						type : "GET",
						success : function(data) {
							$("#road option").remove();
							//	받아온 데이터를 반복하면서 내용을 채운다.
							$roads = $(data).find("road");
							$("#road").append("<option value='0'>=도로명을 선택하세요=</option>");
							$roads.each(function() {
								$road = $(this).text();
								$op = "<option value='"+ $road+"'>"+$road+"</option>";
								$("#road").append($op);
							});
						},
						error : function() {
							alert("에러");
						}
					});
				});
				$("#gugun").change(function() {
					//	할일
					//		시도와 구군을 알아낸다.
					$sido = $("#sido").val();
					$gugun = $("#gugun").val();
					
					//		Ajax를 이용해서 요청한다.
					$.ajax({
						url : "../Member/ZipSearchDong.do",
						data : "sido=" + encodeURIComponent($sido) + "&gugun=" + encodeURIComponent($gugun) + "&temp=" + new Date(),
						dataType : "xml",
						type : "GET",
						success : function(data) {
							//	할일
							//	일단 먼저있던 내용 지운다.
							$("#dong option").remove();
							//	받아온 데이터를 반복하면서 내용을 채운다.
 							$dongs = $(data).find("dong");
							$("#dong").append("<option value='0'>=읍면동을 선택하세요=</option>");
							$dongs.each(function() {
								$dong = $(this).text();
								$op = "<option value='"+ $dong+"'>"+$dong+"</option>";
								$("#dong").append($op);
							});
						},
						error : function() {
							alert("에러다");
						}
					});
				});
				$("#sido").change(function(){
					//	이곳에서 Ajax 기법을 이용해서 서버에 요청을 한다.
					//	1.	선택한 시도를 알아낸다.
					$sido = $(this).val();
					//	물론	$sido = $("#sido").val();	로 해도 된다.						
					$.ajax({
						url : "../Member/ZipSearchGugun.do",
						data : {sido:  $sido}, 
						dataType : "json",
						type : "POST",
						success : function(data) {
							//	응답이 성공하면....
							//	1.	기존에 콤보상자의 내용을 지운다.
							$("#gugun option").remove();	
							//	2.	응답 받은 결과를 이용해서 콤보상자에 내용을 출력한다.
							
							$temp = data.gugun;
							//	배열이 나온다.
							$("#gugun").append("<option value='0'>=구군을 선택하세요=</option>");
							$.each($temp, function(index){
								$op = "<option value='" + $temp[index] + "'>" + $temp[index]+ "</option>";
								$("#gugun").append($op);
							});
						},
						error : function() {
							alert("에러다");
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<table width="95%" border="1" align="center">
			<tr>
				<td align="center">
					<select name="sido" id="sido">
						<option value="0">=시도를 선택하세요=</option>
						<c:forEach var="temp" items="${SIDO}">
							<option value="${temp}">${temp}</option>
						</c:forEach>
					</select>
					<select name="gugun" id="gugun">
						<option value="0">=구군을 선택하세요=</option>
					</select>
					<select name="dong" id="dong">
						<option value="0">=읍면동을 선택하세요=</option>
					</select>
					<select name="road" id="road">
						<option value="0">=도로명을 선택하세요=</option>
					</select>
					<input type="button" value="검색" id="zBtn">
				</td>
			</tr>		
		</table>
		<div id="zip"></div>
	</body>
</html>





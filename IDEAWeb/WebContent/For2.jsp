<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 	3줄짜리 표를 만들자 -->
	<table>
<%	
/*	자바적인 주석 명령 */
for(int i = 0; i < 3; i++) {
%>	
		<tr>
			<td>111</td>
			<td>222</td>
		</tr>
<%
}	//	for 종료
%>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<style>
			table, th, td {
 				border: 1px solid black;
				border-collapse: collapse;
			}
		</style>
	</head>
	<body>
		<table style="width:100%">
			<tr height="700">
				<td width="10%">
					<%@ include file="LeftMenu.jsp" %>
				</td>
				<td width="90%">
					이 부분에는 본문 내용이 들어갑니다. 이쁘게 만들면 되겠다.
				</td>
			</tr>
			<tr height="100">
				<td colspan="2">
					<%@ include file="Copywrite.jsp" %>
				</td>
			</tr>
		</table>
	</body>
</html>






<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<!-- 	��Ϻ��� ����� -->
		<table border="1" align="center" width="80%">
			<tr>
				<th>��ȣ</th>
				<th>����</th>
				<th>�۾���</th>
				<th>÷������</th>
			</tr>
<c:forEach var="temp" items="${LIST}">
			<tr>
				<td>${temp.no}</td>
				<td>${temp.title}</td>
				<td>${temp.writer}</td>
				<td align="center">
	<c:if test="${not empty temp.oriname}">
				<a href="../FileUpload/FileDownload.dol?oriNo=${temp.no}"><img src="../images/down.gif"></a>	
	</c:if>			
				</td>
			</tr>
</c:forEach>			
		</table>
<!-- 	��Ÿ ��� ����� -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<input type="button" id="wBtn" value="�۾���">
				</td>
			</tr>
		</table>
	</body>
</html>

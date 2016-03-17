<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#oBtn").click(function(){
					$(location).attr("href", "../Member/LoginOut.dol");
				});
				$("#mBtn").click(function(){
					//	GET ������� ��û�ϴ� JQeury
					$(location).attr("href", "../index.jsp");
				});
				$("#lBtn").click(function(){
					$id = $("#id").val();
					if($id == "") {
						alert("���̵� �Է����ּ���");
						return;
					}
					$pw = $("#pw").val();
					if($pw == "") {
						alert("��й�ȣ�� �Է����ּ���");
						return;
					}
					
					$frm = $("#lfrm");
					$frm.attr("action", "../Member/LoginProc.dol");
					$frm.submit();
				});
			});
		</script>
	</head>
	<body>
<c:if test="${not empty sessionScope.ID}">
	<!--  �α����� ������....	ȯ���λ縻	-->
	<table width="50%" border="1" align="center">
		<tr>
			<td align="center">${sessionScope.NICK} �� ȯ���մϴ�.</td>
		</tr>
		<tr>
			<td align="center">
				<input type="button" value="�α׾ƿ�" id="oBtn">
				<input type="button" value="��������" id="mBtn">
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${empty sessionScope.ID}">
	<!--  �α����� ���� �ʾ�����....	�α��� ���� ������ְ�-->
	<form method="POST" id="lfrm">
		<table width="50%" border="1" align="center">
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id"></td>
			</tr>	
			<tr>
				<td>Password</td>
				<td><input type="password" name="pw" id="pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="�α���" id="lBtn">
				</td>
			</tr>
		</table>
	</form>
</c:if>
	</body>
</html>








<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script>
			function Write() {
				//	����
				//		����� ������ �Է� �Ǿ����� Ȯ���ϰ�
				var	title = document.getElementById("title").value;
				if(title == "") {
					alert("������ �Է��� �ּ���");
					return;
				}
				var	body = document.getElementById("body").value;
				if(body == "") {
					alert("������ �Է��� �ּ���");
					return;
				}
				//		����� �Է��� �Ǿ����� ������ �����ش�.
				var	frm = document.getElementById("frm");
				//	frm.�Ӽ� = "����";
				//	�ش� �Ӽ��� �����ϴ� ���
				frm.action = "../ReBoard/BoardWrite.reb";
				//	�� �κ��� action �̶�� �Ӽ� ���� �����Ѵ�.
				frm.submit();
			}
			function Reset() {
				//	����ü�� �Է��� ������ �ʱ�ȭ ��Ű�� ���
				//	����>		��.reset();
				var	frm = document.getElementById("frm");
				frm.reset();
			}
			function List() {
				//	GET ����� �̿��ؼ� ��� ���⸦ ��û
				location.href = "../ReBoard/BoardList.reb";
			}
		</script>
	</head>
	<body>
<%--
		�۾��� ���� �����ָ� �ȴ�.
		������ ���� �䵵 �ּ�â���� ���� ������ ��찡 �ִ�.
 --%>
 	<c:if test="${sessionScope.ID eq null}">
 		<c:redirect url="../Member/LoginForm.jsp" />
 	</c:if>
<%--	�ڹٽ�ũ��Ʈ�� jquery�� �̿��ؼ� ���� �۾��� �ϰ� ��������
		�ַ� id ���� �̿��ؼ� Ư�� ��Ҹ� �����Ѵ�.
		������ �ַ� name ���� �̿��ؼ� ���� ó���Ѵ�.
		���	���� ���鶧�� ���������� id, name ���� �� �� �����ϵ��� �Ѵ�.
 --%>	
 	<form method="POST" id="frm" name="frm" action="">
	<table width="50%" border="1" align="center">
		<tr>
			<td>�۾���</td>
			<td><input type="text" disabled value="${sessionScope.ID}"></td>
		</tr>	
		<tr>
			<td>����</td>
			<td><input type="text" name="title" id="title"></td>
		</tr>	
		<tr>
			<td>����</td>
			<td><textarea name="body" id="body"></textarea></td>
		</tr>	
		<tr>
			<td colspan="2" align="center">
				<a href="JavaScript:Write()">�۾���</a>
				<a href="JavaScript:Reset()">�ٽþ���</a>
				<a href="JavaScript:List()">��Ϻ���</a>
			</td>
		</tr>	
	</table>
	</form>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script>
			function	ShowList() {
				//	�ʿ��� �۾��� �ϰ� ���� ��Ϻ���� ��û�ϰ� �ʹ�.
				alert("�� ����Ǵ�");
				location.href = "BoardList.bbs";
				//	<a href>�� ������ ȿ���̴�.
			}
			
			function Reply() {
				//	����
				//		�������� ������ ���� ���Ἲ �˻縦 �ϰ��� �Ѵ�.
				//		��, ������ �Է����� �ʾ����� ����� ���� �����̹Ƿ� ������ ������ ���� �ʿ䰡 ����.
				//	1.	textarea�� �Է��� ���� �˾Ƴ���.
				var	area = document.getElementById("rebody").value;
				//		�Է� ���� ������ �˾Ƴ��� �Ӽ�				value
				//		�Ϲ� HTML ����� ������ �˾Ƴ��� �Ӽ�	innerHTML
				if(area == "") {
					alert("����� �Է��� �ּ���");
					return;
				}
				//	2.	�׷��� ���� ������ ������ ������.
				var	frm = document.getElementById("refrm");
				frm.submit();
			}
		</script>
	</head>
	<body>
<%--
	������ �Խù��� �󼼺��� ������ ����ϰ�
 --%>
		<table width="50%" border="1" align="center">
			<tr>
				<td>�۹�ȣ</td>
				<td>${DATA.NO}</td>
				<td>��ȸ��</td>
				<td>${DATA.HIT}</td>
			</tr>
			<tr>
				<td>�ۼ���</td>
				<td>${DATA.WRITER}</td>
				<td>�ۼ���</td>
				<td>${DATA.DATE}</td>
			</tr>
			<tr>
				<td>����</td>
				<td colspan="3">${DATA.TITLE}</td>
			</tr>
			<tr>
				<td>����</td>
				<td colspan="3">${DATA.CONTENT}</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<%--�ʿ��� ��� ��>	��Ϻ���, �����ϱ�,......--%>
					<a href="JavaScript:ShowList()">��Ϻ���</a>
			<c:if test="${sessionScope.ID eq DATA.WRITER}">
<%--	sessionScope.ID		������ ������ �ִ� ������ �� ID �� 
		sessionScope��		JSTL�� ����ϴ� ���� ������ �ǹ��ϴ� �����
--%>
					<a href="../Board/BoardModifyForm.bbs?oriNO=${DATA.NO}">�����ϱ�</a>
					<a href="">�����ϱ�</a>
			</c:if>
<%--
	����	��û�� �����ϴ� ���
			1.	���� ��û���� ó���ϴ� ���
				http://localhost:8080/IDEAWeb/Board/BoardDetail.bbs		���
			
			2.	��� ��û���� ó���ϴ� ���
				���� ���¸� �������� �ؼ� �޶��� �κи� ����ϴ� ���
				��>	���� ����		http://localhost:8080/IDEAWeb/Board/BoardDetail.bbs
						��û ����		http://localhost:8080/IDEAWeb/Board/BoardList.bbs
						
				<a href="BoardList.bbs">	
			
				��>	���� ����		http://localhost:8080/IDEAWeb/Board/BoardDetail.bbs
						��û ����		http://localhost:8080/IDEAWeb/Member/LoginProc.bbs
			
				<a href="../Member/LoginProc.bbs">
				
				
				�ǹ������� ��� ��û�� �ξ��� ���� ����Ѵ�.
				�ֳ��ϸ� �����ּҴ� �ּҰ� ����Ǹ� ��� ��û�� �ٽ� �����ؾ� �ϱ� �����̴�.
 --%>
				</td>
			</tr>
		</table>
<%--
	�� �Խù��� ������ ����� �����ְ�
--%>
		<c:forEach var="temp" items="${LIST}">
			<table width="50%" border="1" align="center">
			<tr>
				<td>��۹�ȣ</td>
				<td>${temp.NO}</td>
				<td>�ۼ���</td>
				<td>${temp.DATE}</td>
			</tr>		
			<tr>
				<td>�۾���</td>
				<td colspan="3">${temp.WRITER}</td>
			</tr>
			<tr>
				<td>����</td>
				<td colspan="3">${temp.BODY}</td>
			</tr>
		</table>
		</c:forEach>
<%--
	��۾��� �� �����ְ�
	��۾��⸦ �ϱ� ���ؼ��� �������� 
		1.	����
		2.	������ ��ȣ			�� �����־�� �Ѵ�.
		
	������ ��ȣ�� ��� �˷��� ���ΰ�?
	�ڡڡ�
	����	POST ��� �� form�� ���ؼ� ������ ������ ������ ���(submit)���� form�� �ִ� �͸� ������ ���� �� �ִ�.
			form �ۿ� �ִ� ������ ������ ���� �� ����.
	���	���� � �����͸� �ݵ�� ������ ������ �Ѵٸ� �� �����ʹ� �ݵ�� form ���ʿ� �����ϰ� �־�� �Ѵ�.			

	���	���� � �����͸� �ݵ�� ������ ������ �Ѵٸ� �ٵ� �� �����͸� Ŭ���̾�Ʈ���� �����ϰ� ���� �ʴٸ�....
			<input type="hidden" name="" value="">
			�� �۾��� ���ƾ� �Ѵ�.
			
			�̷��� �� ������ ������ value�� ������ ���� ���޵ȴ�.			 		
--%>
		<form method="POST" action="../Board/BoardReWrite.bbs" id="refrm">
			<input type="hidden" name="oriNO" value="${DATA.NO}">
			<table width="50%" border="1" align="center">
				<tr>
					<td>�۾���</td>
					<td><input type="text" name="writer" value="${sessionScope.ID}" disabled></td>
				</tr>		
				<tr>
					<td>����</td>
					<td><textarea name="body" id="rebody" cols="50" rows="5"></textarea></td>
				</tr>		
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="��۾���" onClick="JavaScript:Reply()">
					</td>
				</tr>		
			</table>
		</form>
	</body>
</html>





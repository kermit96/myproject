<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			//	�󼼺��� ��û�� ���� �Լ�
			function goDetail(orino) {
				//	�Ű�����	������ ���� ��ȣ�� ���� �����̴�.
				$(location).attr("href", "../RBoard/BoardHit.dol?nowPage=${PINFO.nowPage}&oriNo=" + orino + "&flag=L");
			}
			$(document).ready(function(){
				$("#sBtn").click(function() {
					//	�˻��ܾ �ԷµǾ����� ���Ἲ �˻��ϰ�....
					$("#sfrm").attr("action", "../RBoard/BoardSearch.dol");
					$("#sfrm").submit();
				});
				$("#wBtn").click(function(){
					//	�۾��� �� ��û�� �Ѵ�.(GET���)
					$(location).attr("href", "../RBoard/BoardWriteForm.dol");
				});
			});
		</script>
	</head>
	<body>
<!-- 	�˻���� -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<form method="POST" id="sfrm">
						<select id="kind" name="kind">
							<option value="title">����</option>
							<option value="body">����</option>
							<option value="writer">�۾���</option>
							<option value="both">���� + ����</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="�˻�" id="sBtn">
					</form>
				</td>
			</tr>
		</table>
<!-- 	��� -->
		<table border="1" align="center" width="80%">
			<tr>
				<th>��ȣ</th>
				<th>����</th>
				<th>�۾���</th>
				<th>�ۼ���</th>
				<th>��ȸ��</th>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.no}</td>
					<td>
<%--	�Ķ���Ͱ� �����̸� ���������....
		�Ķ���Ͱ� �����̸� �ݵ�� ' ' Ȥ�� " "�� ���־�� �Ѵ�.
 --%>
						<a href="JavaScript:goDetail(${temp.no})">${temp.title}</a>
					</td>
					<td>${temp.nick}</td>
					<td>${temp.datetime}</td>
					<td>${temp.hit}</td>
				</tr>
			</c:forEach>
		</table>
<!-- 	������ �̵� -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
				<!-- 	[ó��][����][1][2][3][4][5][����][������] -->
					<a href="../RBoard/BoardList.dol?nowPage=1">[ó  ��]</a>
					<c:if test="${PINFO.startPage eq 1}">
						[�� ��]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../RBoard/BoardList.dol?nowPage=${PINFO.startPage - 1}">[�� ��]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="../RBoard/BoardList.dol?nowPage=${temp}">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[�� ��]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../RBoard/BoardList.dol?nowPage=${PINFO.endPage + 1}">[�� ��]</a>
					</c:if>
					<a href="../RBoard/BoardList.dol?nowPage=${PINFO.totalPage}">[������]</a>
				</td>
			</tr>
		</table>
<!-- 	��Ÿ -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<input type="button" value="�۾���" id="wBtn">
				</td>
			</tr>
		</table>
	</body>
</html>

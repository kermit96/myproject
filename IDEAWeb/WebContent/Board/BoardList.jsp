<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, iedu.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%--
	��� ���⸦ �����
 --%>
 <%
 	//	���� �˷��� �����͸� ������.
 	ArrayList		list = (ArrayList) request.getAttribute("DATA");
 	PageInfo		pInfo = (PageInfo) request.getAttribute("PINFO");
 //	�𵨿��� �Ѱ��� ���� �����ΰ��� ���� �޴� ��ĵ� �޶�����.
 //	��		�𵨿���		session.setAttribute();	�� �ѱ��
 //			��������		session.getAttribute()�� �޴´�.
 %>
		<table border="1" width="80%" align="center">
			<tr>
				<th>��ȣ</th>
				<th>����</th>
				<th>�۾���</th>
				<th>�ۼ���</th>
				<th>��ȸ��</th>
			</tr>
<%
	for(int i = 0; i < list.size(); i++) {
		HashMap	map = (HashMap) list.get(i);	
%>
			<tr>
				<td><%= map.get("NO") %></td>
				<td>
<%--
	GET ������� ��û�� �� �Ķ����		��û����?Ű��=������&Ű��=������
--%>				
					<a href="../Board/BoardDetail.bbs?oriNO=<%= map.get("NO") %>"><%= map.get("TITLE") %></a>
				</td>
				<td><%= map.get("ID") %></td>
				<td><%= map.get("DATE") %></td>
				<td><%= map.get("HIT") %></td>
			</tr>
<%
	}
%>
 		</table>
<%--
	��Ÿ �ΰ����� ����� �����.
	��Ϻ��� ���¿��� �ʿ��� �ΰ����� ����� �۾��� ���߸� ����� �ִ� ���̴�.
 --%> 
		<table border="1" width="80%" align="center">
			<tr>
				<td align="center">
					<a href="../Board/BoardInsertForm.bbs">�۾���</a>
				</td>
			</tr>
		</table>
<%--	������ �̵� ��� �����
		[1][2][3][4][5]
 --%>	
 		<table	 border="1" width="80%" align="center">
 			<tr>
 				<td align="center">
		<c:if test="${PINFO.startPage eq 1}">
 			[����]
 		</c:if>
 		<c:if test="${PINFO.startPage ne 1}">
 			[<a href="../Board/BoardList.bbs?nowPage=${PINFO.startPage - 1}">����</a>]
		</c:if>
		<c:forEach var="i" begin="${PINFO.startPage}" end="${PINFO.endPage}">
			<c:if test="${i eq PINFO.nowPage}">
			[${i}]
			</c:if>
			<c:if test="${i ne PINFO.nowPage}">
			[<a href="../Board/BoardList.bbs?nowPage=${i}">${i}</a>]
			</c:if>
		</c:forEach>
		<c:if test="${PINFO.totalPage eq PINFO.endPage}">
			[����]
		</c:if>
		<c:if test="${PINFO.totalPage ne PINFO.endPage}">
 			[<a href="../Board/BoardList.bbs?nowPage=${PINFO.endPage + 1}">����</a>]		
		</c:if>
				</td>
			</tr>
		</table>
	</body>
</html>

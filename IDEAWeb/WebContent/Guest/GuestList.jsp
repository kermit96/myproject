<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="dao.*, java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	���� �츮�� ������ ���� ������ ��ũ�� �̿��ؼ� �����ϵ��� �����Ѵ�.
	//	������ �˻� ������� �̿��� ��쿡�� ���� �ּҸ� ġ�� ������ ��쵵 �����Ѵ�.
	//	�� ��쿡�� ����ڰ��� ���°� ������ �ʴ� ������ ���� �ִ�.
	//			��>		�α����� �ؾ߸� ������ �̿��� �� �ִµ�.......
	//	���
	//		�� �������� ����� �� �ִ� ������ �ִ����� �Ź� ������ ���־�� �Ѵ�.
	
	String	id = (String)session.getAttribute("ID");
	if(id == null || id.length() == 0) {
		response.sendRedirect("../Member/LoginForm.jsp");
	}
	
%>
<%--
	�ƽôٽ��� ������ ���� ������� ���� ������ �����ְ�
	������ �κп� ������ ���� �ִ� ���� �����ִ� ������ ���Ѵ�.
 --%>
<%--
	���� ���뺸��
 --%>
<%
	//	���� ������ ������ ������ ����Ʈ�� ����� ���;� �Ѵ�.
	GuestDao		dao = new GuestDao();
	ArrayList		list = dao.getGuest();
	dao.close();
%>
<%
	for(int i = 0; i < list.size(); i++) {
		HashMap	map = (HashMap) list.get(i);
%>
		<table width="70%" border="1" align="center">
			<tr>
				<td>�۹�ȣ</td>
				<td><%= map.get("NO") %></td>
			</tr>
			<tr>
				<td>�۾���</td>
				<td><%= map.get("WRITER") %></td>
			</tr>
			<tr>
				<td>��¥</td>
				<td><%= map.get("DATE") %></td>
			</tr>
			<tr>
				<td>����</td>
				<td><%= map.get("CONTENT") %></td>
			</tr>
		</table>		
<%
	}
%>	
<%--
	���� �۾��� ��
 --%>
		<form method="POST" action="GuestInsert.jsp">
			<table border="1" width="70%" align="center">
				<tr>
					<td>�۾���</td>
					<td>
						<input type="text" disabled value="<%= session.getAttribute("ID") %>">
					</td>
				</tr>
				<tr>
					<td>����</td>
					<td>
						<textarea name="body" cols="50" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="���� ����">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>











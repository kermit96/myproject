<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="dao.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	String	id = (String)session.getAttribute("ID");
	if(id == null || id.length() == 0) {
		response.sendRedirect("../Member/LoginForm.jsp");
	}
	
	//	����
	//		�˷��� ���� ������ �޴´�.
	String	body = request.getParameter("body");	
	//		�۾��̸� �˾Ƴ���.
	//		<input text�� disable�� �Ǿ� ������ �� �����ʹ� ������ ���� �ʴ´�.
	//		�׷��Ƿ� �۾��̴� �ٸ� ������� �˾Ƴ� �� �ۿ� ����.
	//		�����̵� �츮�� �۾��� ���̵� ���ǿ� ����� ���Ҵ�.
//	String		id = (String) session.getAttribute("ID");
	
	//		���� ����Ѵ�
	GuestDao	dao = new GuestDao();
	dao.insertGuest(id, body);
	dao.close();	
	//		��Ϻ��⸦ �ٽ� ��û�Ѵ�.
	response.sendRedirect("GuestList.jsp");
%>
	</body>
</html>

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
	//	����
	//		Ŭ���̾�Ʈ�� �˷��� id, pw�� �޴´�.
	String		id = request.getParameter("id");
	String		pw = request.getParameter("pw");
	//		dao���� ���Ǹ� ��Ź�Ѵ�.
	LoginDao	dao = new LoginDao();
	HashMap	map = dao.isMember(id, pw);
	//	�����ͺ��̽� ����� ������.
	dao.close();
//	if(map.isEmpty()) {
		//	���� ����� ������.		ȸ���� �ƴϴ�. 
//	}
//	else {
	if(!map.isEmpty()) {
		//	ȸ���� �±���
		//	�� ȸ���� ������ ���ǿ� �־� �־ ���߿� �ٽ� ����� �� �ֵ��� ��ġ�Ѵ�.
		//	������ �� ����ϴ� ���� �ƴϰ� �ʿ��� �͸� ����ϸ� �ȴ�.
		session.setAttribute("ID", map.get("ID"));
		session.setAttribute("NAME", map.get("NAME"));
		session.setAttribute("NICK", map.get("NICK"));
	}
	//	���������� ������ �ؾ� �ϴµ�... ������ ������ �����ϱ�.... ������ �ٽ� �α��� ȭ������ ������.
	response.sendRedirect("LoginForm.jsp");
%>
	</body>
</html>



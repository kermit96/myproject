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
	
	//	할일
	//		알려준 방명록 내용을 받는다.
	String	body = request.getParameter("body");	
	//		글쓴이를 알아낸다.
	//		<input text가 disable로 되어 있으면 이 데이터는 서버에 오지 않는다.
	//		그러므로 글쓴이는 다른 방식으로 알아낼 수 밖에 없다.
	//		다행이도 우리는 글쓴이 아이디를 세션에 기억해 놓았다.
//	String		id = (String) session.getAttribute("ID");
	
	//		글을 등록한다
	GuestDao	dao = new GuestDao();
	dao.insertGuest(id, body);
	dao.close();	
	//		목록보기를 다시 요청한다.
	response.sendRedirect("GuestList.jsp");
%>
	</body>
</html>

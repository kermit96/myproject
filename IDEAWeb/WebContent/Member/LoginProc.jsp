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
	//	할일
	//		클라이언트가 알려준 id, pw를 받는다.
	String		id = request.getParameter("id");
	String		pw = request.getParameter("pw");
	//		dao에게 질의를 부탁한다.
	LoginDao	dao = new LoginDao();
	HashMap	map = dao.isMember(id, pw);
	//	데이터베이스 사용은 끝났다.
	dao.close();
//	if(map.isEmpty()) {
		//	질의 결과가 없구나.		회원이 아니다. 
//	}
//	else {
	if(!map.isEmpty()) {
		//	회원이 맞구나
		//	이 회원의 정보를 세션에 넣어 주어서 나중에 다시 사용할 수 있도록 조치한다.
		//	무조건 다 기억하는 것이 아니고 필요한 것만 기억하면 된다.
		session.setAttribute("ID", map.get("ID"));
		session.setAttribute("NAME", map.get("NAME"));
		session.setAttribute("NICK", map.get("NICK"));
	}
	//	마지막으로 응답을 해야 하는데... 응답할 내용은 없으니까.... 강제로 다시 로그인 화면으로 보내자.
	response.sendRedirect("LoginForm.jsp");
%>
	</body>
</html>



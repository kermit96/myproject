<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	Hashtable	map = new Hashtable();
	map.put("isundol", "1234");
	map.put("nasundol", "1234");
	map.put("goldgii", "1234");
	
	//	할일
	//		1.	클라이언트가 알려준 id, 비번을 알아낸다.
	String	id = request.getParameter("id");
	String	pw = request.getParameter("pw");
	
	//		2.	이제 map 안에 그런 아이디가 있는지 확인한다.
	if(map.containsKey(id)) {
		//	3.	그 아이디에 해당하는 비밀번호가 일치하는지 확인한다.
		String		mapPw = (String) map.get(id);
		if(mapPw.equals(pw)) {
			//	여기까지 통과한 사람은 회원임이 확실하다.
			//	핵심(☆☆☆)
			//	이 클라이언트의 정보를 세션에 기억해 놓는다.
			//	오늘은 이 클라이언트의 id를 세션에 기억해 놓도록 한다.
			session.setMaxInactiveInterval(60);
			//	참고	시간 단위는 초 단위로 입력한다.
			session.setAttribute("CID", id);
		}
	}
	//	이제 결과를 보여줄 차례이다.
	//	JSP는 항상 실행 결과를 클라이언트에 전달해 주어야 한다.
	//	원칙적으로 JSP는 뭔가를 처리했으면 그 결과를 클라이언트에게 알려주어야 한다.
	//	하지만 대부분의 경우 로그인 후에는 다시 메인 화면으로 응답을 하게 되더라
	//	이 말은 서버가 응답을 하는 대신에 메인 화면으로 응답하도록 요청을 바꾼것이다.
	//	서버가 다른 응답을 하도록 하는 것을
	//	우리가 Redirect 라고 부르고
	//	이것을 가능하게 하는 명령			response.sendRedirect() 이다.
	response.sendRedirect("LoginForm.jsp");
	//	이렇게 명령을 내리면 서버는 다시 LoginForm.jsp를 실행해서 그 결과를 응답하게 된다. 
%>
	</body>
</html>









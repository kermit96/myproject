<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<%	   
	String	tempID = (String) session.getAttribute("id");
	if(tempID == null || tempID.length() == 0) {
		
		String url =request.getRequestURL().toString();
		
		session.setAttribute("url", url );
	/*	
		RequestDispatcher rd =
				  request.getRequestDispatcher("Member/LoginForm.jsp");
		rd.forward(request,response);
		*/
		
				 
		response.sendRedirect("member/login.do");
				
	}
	%>
	
	<body>
		<h1 style="text-align:center">Kermit HOME</h1>
<%--	설문조사 페이지로 하이퍼 링크할 수 있도록 해준다. --%>		
		<a href="MySurvey/SurveyList.jsp">설문조사 문항 보기</a><br>
		<a href="Guest/GuestList.jsp">방문록</a><br>
		<a href="Board/BoarderList.bbs">게시판</a><br>
		<a href="ReBoard/BoarderList.bbs">답글게시판</a><br>
		
		<a href="FileBoard/FileBoarderList.fbd">업로드 게시판</a><br>
		
		<a href="Member/LogoutProc.jsp">로그아웃</a><br>
				
	</body>
</html>
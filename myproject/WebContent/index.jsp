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
<%--	�������� �������� ������ ��ũ�� �� �ֵ��� ���ش�. --%>		
		<a href="MySurvey/SurveyList.jsp">�������� ���� ����</a><br>
		<a href="Guest/GuestList.jsp">�湮��</a><br>
		<a href="Board/BoarderList.bbs">�Խ���</a><br>
		<a href="ReBoard/BoarderList.bbs">��۰Խ���</a><br>
		
		<a href="FileBoard/FileBoarderList.fbd">���ε� �Խ���</a><br>
		
		<a href="Member/LogoutProc.jsp">�α׾ƿ�</a><br>
				
	</body>
</html>
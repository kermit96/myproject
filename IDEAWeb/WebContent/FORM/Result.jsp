<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	POST 방식으로 한글이 넘어오면 역시 깨지게 된다.
	//	이것을 방지 하기 위해서는 데이터를 받는 문서에서
	request.setCharacterEncoding("EUC-KR");
	//	로 등록해서 받아야 한다.

	//	폼에 있는 데이터가 서버에 전달되었을때 서버는
	//		request.getParameter("");	로 받으면 된다.
	//	이때 주의사항
	//		""	안에는		받고싶은 입력요소에 사용된 name 속성값이 들어가야 한다.
	String		name = request.getParameter("irum");
	String		gender = request.getParameter("gender");
	String		h1 = request.getParameter("hobby1");
	String		h2 = request.getParameter("hobby2");
	String		h3 = request.getParameter("hobby3");
%>
	당신의 이름은 <%= name %> 이군요<br>
	당신의 성별은 <%= gender %>이군요<br>
	당신의 취미는 <%= h1 + " " + h2 + " " + h3 %>이군요
	</body>
</html>














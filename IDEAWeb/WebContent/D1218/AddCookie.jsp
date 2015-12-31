<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	쇼핑몰에서 물건을 구매했다고 가정하고 그 물건의 정보를 쿠키로 클라이언트에게 구워주자
	//	1.	쿠키를 만든다.
	Cookie		cook1 = new Cookie("ONE", "Ondol Mate");
	Cookie		cook2 = new Cookie("TWO", "Cicle");
	Cookie		cook3 = new Cookie("THREE", "Dambae");

	//	2.	쿠키를 클라이언트에게 전송한다.
	response.addCookie(cook1);
	response.addCookie(cook2);
	response.addCookie(cook3);
%>
<%--	나머지 결과를 보여주자 --%>
		쿠키를 만들어서 제공했습니다.
	</body>
</html>











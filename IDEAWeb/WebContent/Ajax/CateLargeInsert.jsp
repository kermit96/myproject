<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="jdbc.*, java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	할일
	//	넘어온 데이터 받아서
	String	lname = request.getParameter("lname");
	//	이 내용을 데이터베이스에 등록해라
	WebDB					db = null;
	Connection			con = null;
	PreparedStatement	pstmt = null;
	try {
		db = new WebDB();
		con = db.getCON();
		String	sql = "INSERT INTO GoodsCate VALUES((SELECT 'L' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(gc_Code, 2))), 0) + 1), 3, '0') FROM GoodsCate where  gc_code like '____'), ?)";
		pstmt = db.getPSTMT(con, sql);
		pstmt.setString(1, lname);
		pstmt.execute();
	}
	catch(Exception e) {
		
	}
	finally {
		db.close(pstmt);
		db.close(con);
	}
	//	이제 등록이 되었으므로 다시 카테고리 등록 폼으로 Redirct해주자.
	response.sendRedirect("GoodsCateReg.jsp");
%>
	</body>
</html>

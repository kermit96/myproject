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
	//		넘어온 파라메터를 받는데.....
	//			이름을 알아야 하고
	//			지금 등록할 카테고리를 등록할 대카테고리를 알아야 한다.
	String	mname = request.getParameter("mname");
	String	lcate = request.getParameter("lcate");

	WebDB			db = null;
	Connection	con = null;
	PreparedStatement	pstmt = null;
	try {
		db = new WebDB();
		con = db.getCON();
		String sql = "INSERT INTO GoodsCate VALUES ((SELECT '" + lcate + "M' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(gc_Code, 6))), 0) + 1), 3, '0') FROM GoodsCate WHERE gc_Code LIKE '" + lcate + "____'), ?)";
		pstmt = db.getPSTMT(con, sql);
		pstmt.setString(1, mname);
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


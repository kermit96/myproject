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
	//	����
	//		�Ѿ�� �Ķ���͸� �޴µ�.....
	//			�̸��� �˾ƾ� �ϰ�
	//			���� ����� ī�װ��� ����� ��ī�װ��� �˾ƾ� �Ѵ�.
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
	//	���� ����� �Ǿ����Ƿ� �ٽ� ī�װ� ��� ������ Redirct������.
	response.sendRedirect("GoodsCateReg.jsp");
%>
	</body>
</html>


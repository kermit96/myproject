<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	page import="java.sql.*" %>
<%@page import="iedu.sql.OracleJDBCDao"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	드라이버 로딩
OracleJDBCDao dao = null;
	try {
	  dao = new OracleJDBCDao();
		System.out.println("접속 성공");
	}
	catch(Exception e) {
		System.out.println("접속 실패" + e);
	}
	finally {
		if (dao !=null)
			 dao.closeAll();
	}
	 
     
%>
	</body>
</html>


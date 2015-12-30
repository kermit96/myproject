<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@	page import="java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	드라이버 로딩
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//	이 결과는 서버 콘솔창에 나오게 된다.
		System.out.println("드라이버 로딩 성공");
	}
	catch(Exception e) {
		System.out.println("드라이버 로딩 실패");
	}
	//	컨넥션
	Connection		con = null;
	try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.81:1521:orcl", 
													"scott", "tiger");
		System.out.println("접속 성공");
	}
	catch(Exception e) {
		System.out.println("접속 실패" + e);
	}
	finally {
		try {
			con.close();
		}
		catch(Exception e) {}
	}
	

%>
	</body>
</html>


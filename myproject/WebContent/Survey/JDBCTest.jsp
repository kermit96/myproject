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
	//	����̹� �ε�
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//	�� ����� ���� �ܼ�â�� ������ �ȴ�.
		System.out.println("����̹� �ε� ����");
	}
	catch(Exception e) {
		System.out.println("����̹� �ε� ����");
	}
	//	���ؼ�
	Connection		con = null;
	try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.81:1521:orcl", 
													"scott", "tiger");
		System.out.println("���� ����");
	}
	catch(Exception e) {
		System.out.println("���� ����" + e);
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


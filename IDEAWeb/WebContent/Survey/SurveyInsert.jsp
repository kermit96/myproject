<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%--	���� ���� ������ ��Ź�ϸ� �����ͺ��̽��� ����� ����	--%>	
<%
	//	����
	//		1.	Ŭ���̾�Ʈ�� � �׸��� �����ߴ��� �˾Ƴ���.
	String		dap = request.getParameter("dap");

	//		2.	�� �׸��� �����ͺ��̽��� UPDATE �Ѵ�.
	Connection	con = null;
	Statement		stmt = null;
	try {
		//	����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//	���ؼ�
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.81:1521:orcl", 
													"scott", "tiger");
		//	������Ʈ��Ʈ
		stmt = con.createStatement();
		//	���ǽ���
		String		sql = "UPDATE Survey SET " + dap + " = " + dap + " + 1";
		//	����	1�� �׸��� ����������....
		//		UPDATE Survey SET s_dap1 = s_dap1 + 1;
		//	����	2�� �׸��� ����������....
		//		UPDATE Survey SET s_dap2 = s_dap2 + 1;
		stmt.execute(sql);
	}
	catch(Exception e) {
		System.out.println(e);
	}
	finally {
		stmt.close();
		con.close();
	}
	//		3.	����� �����ش�.
	//	�ƽôٽ��� ����� ����� �����ٰ��� ����.
	//	�Խ��� ����� �ϸ� ��� ������ ��� ����� �����ִ� ������ ����.
	
	//	���⼭�� ���������� ����� �ϸ� �ٽ� ���� ���� ���׺��⸦ �����ְ� �ʹ�.
	response.sendRedirect("SurveyList.jsp");
%>
	</body>
</html>










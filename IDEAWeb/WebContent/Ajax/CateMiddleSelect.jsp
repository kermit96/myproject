<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>

<%@ page language="java" contentType="text/plane; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="jdbc.*, java.sql.*, java.util.*" %>
<%
	//	����
	//	�Ѿ�� �Ķ���� �޴´�.
	String	lcate = request.getParameter("lcate");		//	L001, L002
	WebDB		db = null;
	Connection	con = null;
	Statement pstmt = null;
	ResultSet		rs = null;
	
	JsonArray jsonarray = new JsonArray();
	
	try {
		db = new WebDB();
		con = db.getCON();
		pstmt = db.getSTMT(con);
		String	sql = "SELECT * FROM GoodsCate WHERE gc_Code LIKE '" + lcate + "____'";
		rs = pstmt.executeQuery(sql);
		while(rs.next()) {
			
			JsonObject obj = new JsonObject();
			obj.addProperty("CODE", rs.getString("gc_Code"));
			obj.addProperty("NAME", rs.getString("gc_Name"));			
			jsonarray.add(obj);
	       		
		}
		
		out.print(jsonarray.toString());
		
	}
	catch(Exception e) {
		
	}
	finally {
		db.close(rs);
		db.close(pstmt);
		db.close(con);
	}
	/*
		���� �� ����� json ������� ���� �����ؾ� �Ѵ�.
		[{"CODE":"L001M001", "NAME":"�繫��ǰ"}, {"CODE":"L001M001", "NAME":"�繫��ǰ"}, ...]
	*/
%>



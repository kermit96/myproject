<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>

<%@ page language="java" contentType="text/plane; charset=utf-9" pageEncoding="utf-8"%>
<%@ page import="jdbc.*, java.sql.*, java.util.*" %>
<%
	//	할일
	//	넘어온 파라메터 받는다.
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
		이제 이 결과를 json 방식으로 만들어서 응답해야 한다.
		[{"CODE":"L001M001", "NAME":"사무용품"}, {"CODE":"L001M001", "NAME":"사무용품"}, ...]
	*/
%>



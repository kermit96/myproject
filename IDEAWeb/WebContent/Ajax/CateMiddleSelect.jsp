<%@page import="org.jdom2.output.XMLOutputter"%>
<%@page import="org.jdom2.Element"%>
<%@page import="org.jdom2.Document"%>
<%@page import="com.thoughtworks.xstream.XStream"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="jdbc.*, java.sql.*, java.util.*" %>
<%
	//	할일
	//	넘어온 파라메터 받는다.
	String	lcate = request.getParameter("lcate");		//	L001, L002
	WebDB		db = null;
	Connection	con = null;
	Statement pstmt = null;
	ResultSet		rs = null;
	
	// JsonArray jsonarray = new JsonArray();

   Document  document = new Document();   
   Element root = new Element("Goods");
   
	try {
		db = new WebDB();
		con = db.getCON();
		pstmt = db.getSTMT(con);
		String	sql = "SELECT * FROM GoodsCate WHERE gc_Code LIKE '" + lcate + "____'";
		rs = pstmt.executeQuery(sql);
		
		 
		
		while(rs.next()) {
		
			Element good = new Element("Good");
			Element code = new Element("CODE");
			Element name = new Element("NAME");
			
	//		JsonObject obj = new JsonObject();
	//		obj.addProperty("CODE", rs.getString("gc_Code"));
//			obj.addProperty("NAME", rs.getString("gc_Name"));			
//			jsonarray.add(obj);

            code.setText(rs.getString("gc_Code"));
           name.setText(rs.getString("gc_Name"));
	        good.addContent(code);
	        good.addContent(name);
			root.addContent(good);
		}

//		out.print(jsonarray.toString());

		document.setRootElement(root);
		out.clear();

		String str = new XMLOutputter().outputString(document);
		 out.print(str);		
	}
	

	catch(Exception e) {
		System.out.println(e);
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

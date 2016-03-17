<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="jdbc.*, java.util.*, java.sql.*" %>
<!DOCTYPE html>
<%
	//	원래는 MVC를 이용해서 만들어야 하지만...
	//	과거 방식으로 처리할라고 한다.
	//	1.	넘어온 데이터 받는다.
	String	strNO = request.getParameter("no");
	int		no = Integer.parseInt(strNO);
	
	//	2.	이 번호에 해당하는 뉴스 제목을 꺼낸다.
	String	sql = "SELECT * FROM News WHERE n_NO=" + no;
	WebDB		db = new WebDB();
	Connection	con = db.getCON();
	Statement		stmt = db.getSTMT(con);
	ResultSet		rs = stmt.executeQuery(sql);
	ArrayList	list = new ArrayList();
	while(rs.next()) {
		list.add(rs.getString("n_Title"));
	}
	db.close(rs);	
	db.close(stmt);	
	db.close(con);	
%>
<%--	이 결과를 HTML로 꾸미자 --%>
	<table width="50%" border="1" align="center">
<%
		for(int i = 0; i < list.size(); i++) {
%>
			<tr>
				<td>
					<%= list.get(i) %>
				</td>
			</tr>
<%
		}
%>
	</table>

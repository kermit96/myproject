<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="jdbc.*, java.util.*, java.sql.*" %>
<!DOCTYPE html>
<%
	//	������ MVC�� �̿��ؼ� ������ ������...
	//	���� ������� ó���Ҷ�� �Ѵ�.
	//	1.	�Ѿ�� ������ �޴´�.
	String	strNO = request.getParameter("no");
	int		no = Integer.parseInt(strNO);
	
	//	2.	�� ��ȣ�� �ش��ϴ� ���� ������ ������.
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
<%--	�� ����� HTML�� �ٹ��� --%>
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

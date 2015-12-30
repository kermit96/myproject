<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="iedu.dao.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<style>
			table.out {
				border-collapse: collapse;
				width: 70%;
				margin : auto;
				border-bottom: 1px solid #ddd;
				border-top: 1px solid #ddd;
			}
			table.out td {
				padding: 10px;
				text-align: center;
			}
			table.in {
				border-collapse: collapse;
				width: 90%;
				margin : auto;
			}
			table.in td {
				border-bottom: 1px solid #ddd;
				border-top: 1px solid #ddd;
			}
			
</style>
	</head>
	<body>
<%--	결과 보기 문서 --%>
<%
	//	할일
	//		먼저 데이터베이스 있는 내용을 알아낸다.

	ResultSet		rs = null;
	//	JSP 문서 안에서는 try 작업을 하지 않아도 무방하다.
	//	왜냐하면 JSP 문서는 자동적으로 try 작업을 해 놓았기 때문이다.
	//	하지만 그렇다고  try 를 하지 않으면 예외가 발생할때 예외의 원인을 파악하기가 어렵다.
	//	그러므로 필요하다면 과거처럼  try 처리를 해주는 것이 좋다.

	int[]	daps = new int[4];			//	각각의 항목에 있는 데이터를 기억할 변수
	
		OracleJDBCDao dao = null ;

		try {
	  	 dao = new OracleJDBCDao();
		 Statement stat = dao.getSTMT();
			//	실행
			String	sql = "SELECT * FROM Survey";
			rs = stat.executeQuery(sql);
			rs.next();
			daps[0] = rs.getInt("s_dap1");
			daps[1] = rs.getInt("s_dap2");
			daps[2] = rs.getInt("s_dap3");
			daps[3] = rs.getInt("s_dap4");
	}
	catch(Exception e) {
		System.out.println(e);
	}
	finally {
		if (dao != null) 
		  dao.closeAll();			
		
	}
	//		이것을 이용해서 각각의 항목의 백분율을 계산한다.
	int		total = 0;
	for(int i = 0; i < daps.length; i++) {
		total = total + daps[i];
	}
	//	나는 소수점 이하는 버릴 생각으로 int로 잡았다.
	int[]		per = new int[4];			//	각각의 항목의 백분율을 기억할 변수	
	for(int i = 0; i < daps.length; i++) {
		per[i] = (int)(daps[i] * 100 / total);
	}
	//		이 백분율을 이용해서 결과를 보여준다.
	
	String[]	ex = {"태연", "한지민", "하연수", "김사랑"};
	
%>
	<table class="out">
		<tr>
			<td>
				<table class="in">
		<tr>
			<td colspan="3" align="center">
				세상에서 가장 이쁜 사람으로 크리스마스를 같이 보내고 싶은 사람은 누구입니까?
			</td>
		</tr>
<%
		for(int i = 0; i < per.length; i++) {
%>
		<tr>
			<td width="15%"><%=(i + 1) + " " + ex[i] %></td>
			<td width="70%" >
<%--	그래프를 그리는 방식(가장 고전적인 방식) 
		비율의 갯수만큼 그림을 옆으로 나열하면 그래프 처럼 보일 것이다.
--%>			
<%				for(int j = 0; j < per[i]; j++) { %><img src="../images/<%= (i + 1) %>.png" align="left"><% } %>
			</td>
			<td width="15%"><%= per[i] %> %</td>
		</tr>
<%
		}
%>	 

		<tr>
			<td colspan="3" align="center">
				<a href="SurveyList.jsp">설문 조사 항목 보기</a>
			</td>
		</tr>
	</table>
		</td>
		</tr>
	</table>
	</body>
</html>





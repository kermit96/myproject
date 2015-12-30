<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="iedu.dao.*"  %>   
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"> </script>
</head>
<body>
<%

String sdap = request.getParameter("dap");

OracleJDBCDao dao = null ;

try {
 dao = new OracleJDBCDao();
 Statement stat = dao.getSTMT();
 String sql = "update Survey set " + sdap+" = " +sdap+ "+1" ;
 stat.execute(sql);

 response.sendRedirect("SurveyList.jsp"); 
 
} finally {
	
	if (dao !=null) 
		dao.closeAll();
	
}

%>


</body>
</html>
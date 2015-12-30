<%@page import="iedu.util.util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"> </script>
</head>
<body>
<%String str;
   str = util.GetSha256("abc");
%>

test = <%=str%>
</body>

  

</html>
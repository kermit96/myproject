<%@page import="iedu.util.util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//code.jquery.com/jquery.min.js"> </script>
<script>
$( document ).ready(load);
 
function load()
{
   // $("#div1").load("../ajax/sha256?str="+encodeURIComponent ("한글"));

	$("#div1").load("../ajax/sha256",{str:"한글"});

}


</script>
</head>
<body>
<%String str;
   str = util.GetSha256("한글");
%>

test = <%=str%>
<div id="div1">
</div>

</body>

<script>

</script>
 
</html>
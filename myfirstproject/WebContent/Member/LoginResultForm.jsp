<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<%

	String	tempNick = (String) session.getAttribute("nick");

    boolean isLogin = true;
    
    if (tempNick == null  || tempNick.isEmpty()  ) {    	
    	isLogin = false;
    }
    
%>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>

<script>
	
	$( document ).ready(function() {
		

		
		
	});
	   
	</script>
</head>
<body>


	<table width="50%" border="1" align="center" id="welcome">
	
		<tr>
			<td><%= tempNick %> 님 환영합니다.</td>
		</tr>

		<tr>
			<td align="center"><a href="../index.jsp">대문</a></td>
		</tr>

		<tr>
			<td align="center"><a href="../Member/LogoutProc.jsp">로그아웃</a></td>
		</tr>
		
	</table>
</body>


</html>





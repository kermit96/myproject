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
		
	
		
		if (<%=isLogin%> == false  ) {
			try {
			  $("#welcome").hide(); 		  
			  $("#formid").show();
			} catch ( ex) {
			
				alert(ex);
			}
			  
		   } else {
			   try { 
			   $("#welcome").show();
			   $("#formid").hide();
			   
			   } catch ( ex) {
				
					alert(ex);
				}
		   }

	});
	   
	</script>
</head>
<body>

	<form method="POST" action="LoginProc.jsp" id="formid">
		<table border="1" width="50%" align="center">
			<tr>
				<td width="20%">ID</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="로그인">
				</td>
			</tr>
		</table>
	</form>
	<table width="50%" border="1" align="center" id="welcome">
		<tr>
			<td><%= tempNick %> 님 환영합니다.</td>
		</tr>

		<tr>
			<td align="center"><a href="../index.jsp">대문</a></td>
		</tr>

		<tr>
			<td align="center"><a href="LogoutProc.jsp">로그아웃</a></td>
		</tr>
	</table>
</body>


</html>





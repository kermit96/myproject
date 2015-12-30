<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<body>
<%

   //String name =  (String) session.getAttribute("name");
	String	tempID = (String) session.getAttribute("id");

     String str =request.getRequestURL().toString();
	
%>
		<form method="POST" action="LoginProc.jsp" id="loginform">
			<table border="1" width="50%" align="center">
				<tr>
					<td width="20%">ID</td>
		  			<td>
						<input type="text" name="userid">
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td>
						<input type="password" name="password">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="submit" value="로그인">
					</td>
				</tr>
			</table>		
		</form>

      <table  id="showid"">
           <tr> 
             <td align="center"><%=session.getAttribute("nick")%>님 환영합니다.  </td>
       
           </tr>
           
           <tr> 
            <td align="center">
            <a href="logout.jsp"> 로그아웃 </a>
             <a href="visit/visitForm.jsp"> 방문록 </a>
                       
            </td>
           
           </tr>
         
      </table>
   			
	
	</body>
</html>





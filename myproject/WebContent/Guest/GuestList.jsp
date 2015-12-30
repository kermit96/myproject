<%@page import="java.util.ArrayList"%>
<%@page import="iedu.dao.GuestDao"%>
<%@page import="iedu.data.GeustInfo"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
</head>
<body>
    <%
    
	String	tempID = (String) session.getAttribute("id");
	if(tempID == null || tempID.length() == 0) {
		/*
		RequestDispatcher rd =
				  request.getRequestDispatcher("LoginForm.jsp");
		rd.forward(request,response);
		*/
		
		response.sendRedirect("../Member/LoginForm.jsp");
	}
    
       ArrayList<GeustInfo> array;
       GuestDao dao = new GuestDao();
       array = dao.getGuest();
       for(GeustInfo info : array) 
       { 
    %>
    
 <table border="1" width="70%"" align="center">
 <tr>
     <td>글번호 </td>
        <td><%=info.no %>  </td>   
</tr>
<tr>    
        <td>작성자 </td>
        <td><%=info.writer %>  </td>   
</tr>
<tr>    
       <td>작성일  </td>
        <td><%=info.date %>  </td>
 </tr>
 <tr>          
       <td>내용 </td>
        <td><%=info.content.replaceAll("\r\n", "<br>") %>  </td>   
        
 </tr>
 </table>
    <%
       }
    %>
  
    <form method="post" action="GuestInsert.jsp">
    <table border="1" width="70%"" align="center">
         <tr>
            <td>글쓴이</td>
            <td> 
                  <input type="text"  disabled value="<%=session.getAttribute("id") %>" >
                                   
            </td>
         </tr> 
          <tr>
               <td > 
                  내용

               </td>
               <td > 
				<textarea  name="content"  rows="10"  cols="50">

                 </textarea>				                  
               </td>
          </tr>     
          
          <tr>
            <td colspan="2" align="center">  <input type="submit" value=" 전송" >     </td>
          </tr>
         
    </table>
    
    
    </form>
    

</body>
</html>
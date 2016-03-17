<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
$(document).ready(function() {	
	  init();
});

function init()
{
		
//    $("#headtitle").text("Board");    
//	$("#name").val("title");
}


</script>
</head>
<body>
 <%@ include file="../include/top.jsp" %>
 
   <div align="center">
   <h1  id="headtitle"> </h1>
   <form>
        <table>
        
         <tr>
        <td>
           이름 
         </td>
         <td>
           <input id="name" type="text"  readonly  >   
         </td>                    
         </tr>
        
        
        <tr>
        <td>
           제목 
         </td>
         <td>
           <input id="title" type="text" >   
         </td>                    
         </tr>
         
        
         
         <tr>
         <td>
           내용 
         </td>
         <td>
           <textarea id="content"> </textarea> 
         </td>         
        </tr>
               
        </table>
                
   </form>
   </div>  
    
</body>
</html>
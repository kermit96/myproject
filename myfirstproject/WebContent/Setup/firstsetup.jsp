<%@page import="iedu.util.util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="iedu.util.* " %>  
<%
   boolean isview = false;
    //  local 에서  실행하지 않으면 실행되지 않게 한다.    
    String ip=  request.getRemoteAddr();      
    if (util.IsMyIp(ip) )
   	isview = true;     		
%>    
<!DOCTYPE html >

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
<%if (isview)  { %> 
$(document).ready(function() {
	  // 처음 설정 항목을 가져온다.
	  init();
	   
	   $("#save").click(save);
	   $("#end").click(myclose);	   
	   
	   
});


function init()
{

    $.ajax({
        url:'../ajax/getdirconfig',
        async:false,
        type:'post',
        dataType:'json',
        success:function(data){        	   
          $("#dir").val(data.dir);
          
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }
        
    });
}


function save()
{
	$.ajax({
        url:'../ajax/dirsave',
        async:false,
        type:'post',
        dataType:'html',
        data:{dir:$("#dir").val()        	
        },
        success:function(data){            	
            alert("저장했습니다.");
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }        
    }); 	
}


function myclose()
{
	self.opener = self;
	window.close();

}
<%} %>

</script>
</head>

<body>
<%if (isview)  { %>
  <h1 align="center"> 환경 설정 디렉토리</h1>    <p>
  
  <table align="center">
   <tr> 
      <td>
          DB 선택  
      </td>
      <td>
        <input type="text"  id="dir">        
  
      </td>
      
   </tr>
   

  <tr>
  <td colspan="2"  align="center">
    <p>
         <button  id="save"> 저장</button> <button id="end"> 끝내기</button> 
  </td>
  </tr>    
  </table>    
         
   <% } else { %>  
       로컬 pc 에서만 실행 할수 있습니다.
<%} %>

</body>

</html>
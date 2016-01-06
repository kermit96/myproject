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
$(document).ready(function() {
	  // 처음 설정 항목을 가져온다. 
	   $("#test").click(test);
	   $("#save").click(save);
	   $("#end").click(myclose);	   
	   init();
	   
});


function init()
{
    $.ajax({
        url:'../ajax/getdbconfig',
        async:false,
        type:'post',
        dataType:'json',
        success:function(data){            	
          $("#dbname").val(data.dbname);
          $("#dbselect").val(data.dbtype);
          $("#dbuser").val(data.userid);
          $("#dbhost").val(data.host);
          $("#dbport").val(data.port);
          $("#dbpassword").val(data.password);
        }
        
    });
}

function test()
{
	 
}

function save()
{
	
}


function myclose()
{
	
}


</script>
</head>

<body>
<%if (isview)  { %>
  <h1 align="center"> 환경 설정</h1>    <p>
  
  <table align="center">
   <tr> 
      <td>
          DB 선택  
      </td>
      <td>
      
         <select id="dbselect" > 
  
     <option value="0">Oracle</option>
     <option value="1">Ms-sql</option>
      <option value="2">Mysql</option>
          </select>
      </td>
      
   </tr>
   
     <tr> 
      <td>
          DB Host  
      </td>
      <td>
       <input type="text"  id="dbhost" >   
      </td>     
   </tr>

     <tr> 
      <td>
          DB Port (0 이면  default )  
      </td>
      <td>
       <input type="text"  id="dbport"  value="0">   
      </td>     
   </tr>
   
    <tr> 
      <td>
          DB 명   
      </td>
      <td>
       <input type="text"  id="dbname"  value="">   
      </td>     
   </tr>   
  
    <tr> 
      <td>
          userid   
      </td>
      <td>
       <input type="text"  id="dbuser"  value="">   
      </td>     
   </tr>   
  
    <tr> 
      <td>
          password   
      </td>
      <td>
       <input type="password"  id="dbpassword"  value="">   
      </td>     
   </tr>   
  
  <tr>
  <td colspan="2"  align="center">
    <p>
      <button id="test"> 테스트</button>    <button  id="save"> 저장</button> <button id="end"> 끝내기</button> 
  </td>
  </tr>    
  
  </table>    
   
   
   
      
   <% } else { %>  
       로컬 pc 에서만 실행 할수 있습니다.
<%} %>

</body>

</html>
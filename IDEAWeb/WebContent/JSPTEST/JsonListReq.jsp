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
});

function SendAjax()
{

	$.ajax( 
      { 
    	  
    	 url:"JsonListResp.jsp",
    	 dataType:"json",
    	 type:"post",    		 
        success:function(data) {
        	var h="<table width='50%' border='1' align='center' > ";
        	
        	$.each(data,function(index) {
        		h +="<tr>";
        		h += "<td>"
        	    var   temp = data[index];
        		h += temp;
        		h += "</td>"
        	    h +="</tr>";
        	} );
          
        	h+="</table>"
        	$("#div1").html(h);
          },  
      	
          
      
      error:function() {
    	  alert("에러");
      }

      }			
	);

	
	}


</script>
</head>
<body>
<div id="div1"> </div>
 <input type="button" value="눌러"  onClick="SendAjax()"/>
</body>
</html>
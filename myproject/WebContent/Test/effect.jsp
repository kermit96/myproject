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
	$("#hide").click( function () {
		 		 
		       var isview = $("p").is(":visible")  ;
		       
				if (isview==false)
  			      $("#hide").html("hide");
				else 
					$("#hide").html("show");
					
		        $("p").toggle();
		 
		
	} );
	
 $("#show").click( function () {
	

	 try { 
	 
	    $("div").animate({width:'600px'},4000);
    } catch (ex) {
    	
    	alert(ex);
    }
	
} );
	   
});
</script>
</head>
<body>
<div style="background:#98bf21;height:100px;width:100px">
<p>     If you click on the "Hide" button, I will disappear.</p>
</div>
<button id="hide" >Hide </button>
<button id="show">Show</button>
</body>
</html>
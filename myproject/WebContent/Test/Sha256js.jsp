<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"> </script>
<script type="text/javascript" src="../js/sha256.js"> </script>
<script>
$(document).ready(function() {
    // hash listener
    $('#generate-hash').click( function() {
    	
        var t = new Date();
        hash = Sha256.hash($('#message').val());        
        var t2 = new Date() - t;

        $('#hash').val(hash);
       try { 
        $('#hash-time').html(t2+'ms');
      } catch(ex) {
    	  
    	  alert(ex);
      }
    });

});

    
</script>
</head>
<body>
     Message    <input  type="text"   id = "message"> <br>
     
     Hash           <input  type="text"   id = "hash"> <br>
           
     HashTime       <div id="hash-time">  </div><br>     
     
     <input type="button"  id = "generate-hash"  value="hash generator">
      
</body>
</html>
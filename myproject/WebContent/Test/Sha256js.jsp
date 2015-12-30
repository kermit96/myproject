<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//code.jquery.com/jquery.min.js"> </script>
<script type="text/javascript" src="../js/sha256.js"> </script>
<script>
$(document).ready(function() {	
    // hash listener
    jQuery.ajaxSetup({async:false});
    $('#generate-hash').click( function() {
    	
        var t = new Date();
        hash = Sha256.hash($('#message').val());        
    

        $('#hash').val(hash);
        
       
        $("#serverhash0").load("../ajax/sha256",{str:$('#message').val()}, function(response, status, xhr) {
        	  if (status == "error") {
        	    var msg = "Sorry but there was an error: ";
        	    $("#error").html(msg + xhr.status + " " + xhr.statusText);
        	  } else {
        		    
        		  $("#serverhash0").val(response);
        	  }
       	});
       
       
       try {
        $.ajax({
            url:'../ajax/sha256',
            async:false,
            type:'post',
            data:{str:$('#message').val()  },
            success:function(data){
            	
            	  $("#serverhash").val(data);
            }
        });

       } catch (ex) {
    	   
    	   alert(ex);
       }
        
       var t2 = new Date() - t;
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
     
     Hash           <input  type="text"   id = "hash" width="100"> <br>
     
     Server Hash0 <input type="text"   id = "serverhash0" width="100"> <br>
      Server Hash <input type="text"   id = "serverhash" width="100"> <br>
           
     HashTime       <div id="hash-time">  </div><br>     
     
     <input type="button"  id = "generate-hash"  value="hash generator">
      
</body>
</html>
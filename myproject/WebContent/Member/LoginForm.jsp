<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>


<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>

<script>
	
	$( document ).ready(function() {
		
	      $("#login").click(
	    		  login
	      );

	});
	
	
	function login()
	{
		
		var userid = $("#userid").val().trim();
		var password = $("#password").val().trim();
		
		if (userid == "") {
			
		   	alert("userid 를 입력해 주시기 바랍니다.");
			return;
		}
		
        if (password == "") {
			
		   	alert("password 를 입력해 주시기 바랍니다.");
			return;
		}
		

        try {
        $.ajax({
            url:"../ajax/login",
            async:false,
            type:'post',
            dataType:'json',
            data:{userid:$('#userid').val(),password:$('#password').val()  },
            success:function(data){
    

            	 if (data.isSuccess)
                 {   
            		 alert(data.url);
            		 location.href=data.url;            		 
            	 } else {
            		 
            		 alert(data.failreason);
            	 }
            	
              
            },
            error:function(request,status,error){
           //     alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
               }
            	                                    
        });
		
        } catch (ex) {        	
        	alert("ajax"+ex);
        }
	}
	   
	</script>
</head>
<body>

	<form method="POST"  id="formid">
		<table border="1" width="50%" align="center">
			<tr>
				<td width="20%">ID</td>
				<td><input type="text" id="userid"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" id="password"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input id="login" type="button" value="로그인">
				</td>
			</tr>
		</table>
	</form>

</body>


</html>





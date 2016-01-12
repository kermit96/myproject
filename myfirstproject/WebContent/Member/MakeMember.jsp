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
	 $("#ok").click(insertmember);
	 $("#cancel").click(cancel );
});


function insertmember()
{
	var userid = $("#userid").val().trim();
	var password = $("#password").val().trim();
	var repassword = $("#repassword").val().trim();
	var tel =  $("#tel").val().trim();
	var nickname =  $("#nickname").val().trim();
	var name =  $("#name").val().trim();
	
	if (userid == "") {
		
	   	alert("userid 를 입력해 주시기 바랍니다.");
		return;
	}
	
    if (password == "") {
		
	   	alert("password 를 입력해 주시기 바랍니다.");
		return;
	}
    
    if (password != repassword) {
    	
    	alert("password 가 일치하지 않습니다.");
		return;
    }
    
	
//     password = Sha256.hash(password);
    
    /*
      	String userid = request.getParameter("userid");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
      
    
    
    */
    
    try {
    $.ajax({
        url:"../ajax/memberinsert",
        async:false,
        type:'post',
        dataType:'html',
        cache:false,
        data:{userid:userid,password:password, name:name,tel:tel,nickname:nickname  },
        success:function(data){
          alert("가입했습니다.")
          self.opener = self;
      	  window.close();
        },
        error:function(request,status,error){
             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
           }
        	                                    
    });
	
    } catch (ex) {        	
    	alert("ajax="+ex);
    }
}

function cancel()
{
	self.opener = self;
	window.close();
}
</script>
</head>
<body>
  <div align="center">
  <h1> 사용자 가입  </h1>
 <table>
    <tr> 
      <td>userid</td>
      <td><input id="userid" type="text">   </td>      
    </tr>
    
     <tr> 
      <td>성명</td>
      <td><input id="name" type="text">   </td>      
    </tr>
    
     <tr> 
      <td>닉네임</td>
      <td><input id="nickname" type="text">   </td>      
    </tr>
    
    
     <tr> 
      <td>전화번호</td>
      <td><input id="tel" type="text">   </td>      
    </tr>
    
    <tr> 
      <td>비밀번호</td>
      <td><input id="password" type="password">   </td>      
    </tr>
    
    
    <tr> 
      <td>비밀번호 확인</td>
      <td><input id="repassword" type="password">   </td>      
    </tr>
    
    <tr>
    <td colspan="2">
        <button id="ok" > 가입</button>
        <button id="cancel" >끝내기 </button>
    </td>
    </tr>
    
 </table>
</div>
</body>
</html>
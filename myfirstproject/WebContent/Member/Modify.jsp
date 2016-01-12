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
	 $("#save").click(updatemember);
	 $("#cancel").click(cancel );
	
});


function init()
{
	 try {
 	    $.ajax({
 	        url:"../ajax/userinfo",
 	        async:false,
 	        type:'post',
 	        dataType:'json',
 	        cache:false, 	      
 	        success:function(data){
 	        	$("#name").val(data.name);
 	        	$("#nickname").val(data.nickname);
 	        	$("#tel").val(data.tel);
 	     
 	        },
 	        error:function(request,status,error){
 	             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 	           }
 	        	                                    
 	    });
 		
 	    } catch (ex) {        	
 	    	alert("ajax="+ex);
 	    }         
  
	
}

function cancle()
{
	window.back();
}

function updatemember() {
     if ($("#name").val()==""){
       alert("이름을 입력해 주시기 바랍니다.");	 
    	return false; 
    }
     
     if ($("#nickname").val()==""){
         alert("별명을 입력해 주시기 바랍니다.");	 
      	return false; 
      }
     
     if ($("#tel").val()==""){
         alert("전화번호를 입력해 주시기 바랍니다.");	 
      	return false; 
      }
     
     var name =$("#name").val();
     var nickname =$("#nickname").val();
     var tel =$("#tel").val();
     try {
    	    $.ajax({
    	        url:"../ajax/membermodify",
    	        async:false,
    	        type:'post',
    	        dataType:'html',
    	        cache:false,
    	        data:{name:name,tel:tel,nickname:nickname  },
    	        success:function(data){
    	          alert("수정했습니다.")
    	      	   window.back();
    	        },
    	        error:function(request,status,error){
    	             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	           }
    	        	                                    
    	    });
    		
    	    } catch (ex) {        	
    	    	alert("ajax="+ex);
    	    }         
     
}

</script>
</head>
<body>

  <%@ include file="../include/top.jsp" %>

<div  align="center">
 
   <table>
     <tr>
       <td>
         이름 
        </td>
        <td>
         <input type="text" id="name"  name="name" ></input> 
        </td>
      </tr>
     
     <tr>  
         <td>
         별명 
        </td>
        <td>
         <input type="text" id="nickname" name="nickname" ></input> 
        </td>          
     </tr>
      <tr>  
         <td>
         전화 번호 
        </td>
        <td>
         <input type="text" id="tel"  name="tel" ></input> 
        </td> 
         
     </tr>
     
     <tr>
     <td colspan="2">
           <input type="button"  id="save" value="저장" >
            
           <input type="button"   id="cance'" value="취소" >  
     </td>
     </tr>
   
   </table>
 
</div>
</body>
</html>
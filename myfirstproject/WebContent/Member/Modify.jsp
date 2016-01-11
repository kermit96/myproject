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
	// 
	
});

function cancle()
{
	window.back();
}

function validateForm() {
     if ($("name").val()==""){
       alert("이름을 입력해 주시기 바랍니다.");	 
    	return false; 
    }
     
     if ($("nickname").val()==""){
         alert("별명을 입력해 주시기 바랍니다.");	 
      	return false; 
      }
     
     if ($("tel").val()==""){
         alert("전화번호를 입력해 주시기 바랍니다.");	 
      	return false; 
      }
     
     if ($("tel").val()==""){
         alert("전화번호를 입력해 주시기 바랍니다.");	 
      	return false; 
      }
     
     
}

</script>
</head>
<body>
<div  align="center">
 <form action="../memberservelet/modifysave"   onsubmit="return validateForm()">
   <table>
     <tr>
       <td>
         이름 
        </td>
        <td>
         <input type="text" id="name"  name="name"></input> 
        </td>
      </tr>
     
     <tr>  
         <td>
         별명 
        </td>
        <td>
         <input type="text" id="nickname" name="nickname"></input> 
        </td>          
     </tr>
      <tr>  
         <td>
         전화 번호 
        </td>
        <td>
         <input type="text" id="tel"  name="tel"></input> 
        </td> 
         
     </tr>
     
     <tr>
     <td colspan="2">
           <input type="submit"  value="저장" >
            
           <input type="button"   onclick="cancle"  value="취소" >  
     </td>
     </tr>
   
   </table>
   </form>
</div>
</body>
</html>
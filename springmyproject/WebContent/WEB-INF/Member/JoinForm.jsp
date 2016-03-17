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
	
	  
	  $("#zBtn").click(function() {
		  window.open("../Member/ZipSearchForm.do","addrfind") 
	  });
});
</script>
</head>
<body>

  <table  width="50%"  boarder="1" aling="center" >
    <tr> 
      <td> 우편 번호</td>
      <td> 
          <input type="text" name="zipcode" id="zipcode" >
          <input type="button"  value="우편번호검색"  id="zBtn">
          
          </td>                   
    </tr>
    
       <tr>
            <td> 주소 </td>
          <td><input type="text" name="address"   id="address" >  
         </tr>
  
  </table>

</body>
</html>
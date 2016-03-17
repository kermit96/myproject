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

function  ReqAjax()
{
	
   $.ajax({
	 url:"JsonMapResp.jsp",
	 dataType:"json",
	 type:"post",
	 success:function(data) {
		 try {
			 $("#tableid").show();
		   $("#name").html( data.이름);
		   $("#age").html( data.나이);
		   $("#gender").html( data.성별);
		   $("#weight").html( data.신장);
		 } catch (ex) {
			 
			 alert(ex);
		 }
	 },
	 error:function(hdr) {
		 alert("error==>"+hdr);
	 }	 
   });
   
}
   
</script>
</head>
<body>
<table style="display:none" id="tableid">
<tr>
<td> 이름 </td>
<td id="name" > </td>
</tr>
<tr>


<td> 나이 </td>
<td id="age" > </td>
</tr>
<tr>
<td> 생별 </td>
<td id="gender" > </td>

</tr>
<tr>
<td> 몸무게 </td>
<td id="weight" > </td>

</tr>


</table>
<a href="#"  onclick="ReqAjax()">눌러</a>
</body>
</html>
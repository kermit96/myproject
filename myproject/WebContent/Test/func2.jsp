<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
  var  x = sum2;
	 function test() {
		 
		 var result = x(6,4);
		 alert(result);
	 }
	 
	 function sum2(a,b) {
		 
		 var sum=a+b;
		    return sum;
		      	 
	 }
	 
</script>

</head>
<body>

</body>
 <input type="button" onclick="test()" value="눌러">

</html>
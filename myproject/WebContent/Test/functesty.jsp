<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script> 

function max1() {
	
    var max = 0;
    for(var i=0;i<arguments.length;i++) {
    	
    	 if (max <arguments[i]) {
    		 
    		 max = arguments[i]
    	 }
    }

	 return max;
}

  function test() {
      var max = max1( 1,5,6,32,67,37);
      alert(max);
  }
</script>
</head>
<body>
 <input type="button" onclick="test()" value="눌러">
</body>
</html>
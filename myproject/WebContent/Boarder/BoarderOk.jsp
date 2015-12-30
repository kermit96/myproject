<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
 function change()
 {
	 location.href = "../Board/BoarderList.bbs";	 
 }
</script>
</head>
<body>
     <div align="center">
        저장 했습니다 <br>
         <input type="button" value="확인" onclick="change();">
     </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
$(document).ready(function() {	
});

function Write() {

	 alert("vv");
	if ($("#title").val()=="") {
		
		alert("제목을 입력하세요");
		return;
	}
	
	if ($("#content").val()=="") {
		alert("내용을 입력하세요");
		return;
	}

	
   var frm = $("#frm");
   frm.action="../ReBoard/BoardWrite.reb";
   frm.submit();
}

function Reset()
{		
	 $("#frm")[0].reset();
     	 
}

function List() {
	
	
}

</script>
</head>
<body>
<!--  
글쓰기 폼을 보야주면 된다.
  -->
  
   <c:if test="${sessionScope.ID ne null}" >
     <c:redirect  url="../member/login.do"> </c:redirect>
  </c:if>
  
  <form method="post" id="frm" name="frm" >
     <table>
      <tr>
          <td>글쓴이  </td>
           <td> <input type="text" name="title" id="title"  disabled value="${sessionScope.ID}">  
      <tr>
      <tr>
          <td>제목  </td>
           <td> <input type="text" name="title" id="title"> 
      <tr>
      
      <tr>
          <td>내용 </td>
           <td> <textarea rows="5" cols="20" id="content" name="conetent"></textarea> 
      <tr>
      
      <tr>
         <td colspan="2">
            <a href="javascript:Write()" >글쓰기  </a> 
      <a href="javascript:Reset()" > 다시쓰기 </a>
      <a href="javascript:List()" > 목록 보기 </a>
         </td>
      </tr>
     
     </table>
  </form>
</body>
</html>
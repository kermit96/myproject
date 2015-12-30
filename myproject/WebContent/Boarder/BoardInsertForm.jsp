<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script> 
  function golist()
  {
	   location.href = "../board/Board/BoarderList.bbs";
	  
  }
   
</script>
</head>
<body>
    <form method="POST" action="../Board/BoarderInsert.bbs">
    <table>
    <tr>
      <td>글쓴이</td>
      <td>
          <input type="text" name="writer"  disabled value="<%=session.getAttribute("name") %>">
      </td>
    </tr>
    <tr>
    <td>
      제목 
       </td>
    <td>
        <input type="text" name="title"   >
    </td>
    </tr>
    <tr>
    <td>본문</td>
      <td>
         <textarea name="body" cols="50" rows="5" >
         
         </textarea>
      </td>
    </tr>
    <tr>
    <td >
              <input type="submit"  value="쓰기"  >               
    </td>
        
    <td >
              <input type="button"  value="취소"  onclick="golist();" >    
    </td>
    
      
    </tr>
    </table>
    </form>
</body>
</html>
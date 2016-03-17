<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
$(document).ready(function() {

  $("#wBtn").click(function()
	 {
	  
      try {
	        $title=  $("#title").val();
	        if ($title=="") {
	        	 alert("제목을 입력해 주시오");
	        	 return;
	        	
	        }
	        
	        $body=  $("#body").val();
	        if ($body=="") {
	        	 alert("내용을  입력해 주시오");
	        	 return;
	        	
	        }
	  
	        $("#frm").attr("action","../Notice/NoticeWrite.do");
	  
	        $("#frm").submit();
	       } catch(ex) {
	    	   alert(ex);
	       }
	 }	  
  );
  
});
</script>
</head>
<body>    
    <form method="POST" name="frm" id="frm">
        <table width="50%"  align="center"  border="1" > 
           <tr> 
             <td> 유형 </td>
             <td>
                <select id="kind" name="kind" >
                  <option value="1" > 긴급 </option>
                  <option value="2" > 부서 공지 </option>
                  <option value="3" > 일반 공지  </option>
                
                </select>
           
           </tr>
           <tr>
              <td>글쓴이  </td>
              <td><input type="text"  name="writer"  id="writer"> </td>
           </tr>
           
           <tr>
              <td>제목  </td>
              <td><input type="text"  name="title"  id="title"> </td>
           </tr>
           
           <tr>
              <td>내용  </td>
              <td><textarea name="body" id="body"> </textarea></td>
              
           </tr>
           
           <tr>
              
              <td colspan="2" align="center"><input type="button" value="글쓰기" id="wBtn"></td>
              
              
           </tr>
           
           
        </table>
    
    </form>
</body>
</html>
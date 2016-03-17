<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery.min.js">
	
</script>
<script>
	$(document).ready(function() {
		$("#rBtn").click(Resetfun);
		
		$("#wBtn").click(function() {
			
			 $("#frm").attr("action","../FileBoard/FileBoardUpload.fbd");   
			 $("#frm").submit();
			
		});
		
	});
	
	function Resetfun()
	{
		 $("#frm").each(
				 function() {
                this.reset();                           					 
				 
		 });		
	}
	
</script>
</head>
<body>
<%-- 
   지금부터 파일 업로드가 가능한 폼을 제작한다.
   파일 업로드가 가능한 폼은 일반 폼과  별반 차이는 없으나 .. 
   차이점 
      
     1. <form에서 반드시 enctype="multipart/form-data" 로 지정해야ㅐ 한다.
         이렇게 지정하지 않으면 (지금까지 방식)
          parameter 전송이라고 해서 폼안에 있는 데이터만 서버에 전송되는 방식이다.
          enctype 을 지정하년 
          스트림 전송 방식이라고 하여
          폼안에 있는 데이터가 약속된 규틱에 의해서 스트림으로 변환되어서 전송되는 방식이다
          
 --%>
 
 <form  method="post" id="frm"  name="frm"  enctype="multipart/form-data" a>
             <table  > 
               <tr>
                  <td> 글쓴이 </td>
                  <td> <input type="text"  name="writer"  id="writer">  <td>  
               
               </tr>
               
               <tr>
                  <td> 제목  </td>
                  <td> <input type="text"  name="title"  id="title">  <td>  
               
               </tr>
               
                 <tr>
                  <td> 본문  </td>
                  <td> <textarea  name="body"  id="body">  </textarea></td>  
               
               </tr>
               
                 <tr>
                  <td> 업로드 파일   </td>
                  <td> <input type="file"   id="upfile"  name="upfile"  type="file" >  <td>  
               
               </tr>
             
               <tr>
                 <td colspan="2">   
                  <input type="button"   value="글쓰기"  id="wBtn"> 
                   <input type="button"   value="재작성"  id="rBtn">  
                 <input type="button"  value="목록보기"  id="lBtn" >
                  </td>
               </tr>
             
             </table>
 
 </form>
</body>
</html>
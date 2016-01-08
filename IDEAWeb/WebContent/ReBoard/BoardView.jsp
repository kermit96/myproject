<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
		<title>Insert title here</title>
		<script type="text/javascript" src="//code.jquery.com/jquery.min.js"> </script>
		<script>
		
		$(document).ready(function() {
			
			 
			if ("${DATA.writer}" != "${sessionScope.ID}") 
			{
				$("#modify").hide();
				$("#delete").hide();
			} 
			
		});
		
		
		function Modify() {
			  
			  
			  
		  }
		
		  function Delete() {			  
// 삭제 허락 받기 		

              var r = confirm("삭제 하시겠습니까?");
              if(r == false) {
            	  
            	  return;
              }
              
              
				$.ajax({
		            url:'../ajax/reboarddelete',
		            async:false,
		            type:'post',
		            data:{no:$("#id").val() },
		            dataType:'json',
		            success:function(data){
		            	try {
		            		
		            		if (data.result == true) { 
		            		alert("삭제 했습니다.");
		            		location.href = "../ReBoard/BoardList.reb";
		            	   } else {
		            		  alert(data.reason); 		            		   
		            	   }
		            	} catch(ex) {		            		
		            		alert(ex);
		            	}
		            } ,          		            		           
		            error:function(xhr)
		            {
		            	alert("error==>"+xhr.status + " " + xhr.statusText);
		            	
		            }		           		    	 
		     });
		     
				
			 			 
		  }
		
			function List() {
				location.href = "../ReBoard/BoardList.reb?nowPage=${NOWPAGE}";
				//	JSTL은 이처럼 자바 스크립트 안에서도 사용할 수 있다.
			}
			
			function Reple() {
				//	GET 방식 요청
				location.href = "../ReBoard/BoardReWriteForm.reb?oriNO=${DATA.no}"
				//	데이터 빈에있는 데이터를 사용하는 경우는
				//	get 함수의 이름을 이용하면 되는데.....
				//	첫글자는 반드시 소문자로 써야 한다.
				//	나머지 글자는 똑같이 사용해야 한다.
			}
			
			function Bad() {
			
				$.ajax({
		            url:'../ajax/badclick',
		            async:false,
		            type:'post',
		            data:{no:$("#id").val() },
		            dataType:"json",
		            success:function(data){
		            	try {
                         
		            	  $("#cancelspan").html("( "+data.BadCount+" )");
		            	  $("#okspan").html("( "+data.GoodCount+" )");
		            	} catch(ex) {		            		
		            		alert(ex);
		            	}
		            } ,          
		            		            
		            error:function()
		            {
		            	alert("error");
		            	
		            }		           		    	 
		     });
		     
				
				
			}
			
			function Good() {
		// 		location.href = "../ReBoard/BoardGood.reb?oriNO=${DATA.no}&nowPage=${NOWPAGE}";
		    
		     $.ajax({
		            url:'../ajax/goodclick',
		            async:false,
		            type:'post',
		            data:{no:$("#id").val() },
		            dataType:"json",
		            success:function(data){                        
		            	  $("#cancelspan").html("( "+data.BadCount+" )");
		            	  $("#okspan").html("( "+data.GoodCount+" )");
		            } ,          
		            		            
		            error:function()
		            {
		            	alert("error");		            	
		            }		           		    	 
		     });
		        
			}
		</script>
	</head>
	<body>
	    <input type="hidden"  id="id" value="${DATA.no}">
		<table width="50%" border="1" align="center">
			<tr>
				<td>번호</td>
				<td>${DATA.no}</td>
				<td>조회수</td>
				<td>${DATA.hit}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${DATA.writer}</td>
				<td>날짜</td>
				<td>${DATA.date}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${DATA.title}</td>
			</tr>
			<tr>
				<td>본문</td>
				<td colspan="3">${DATA.brbody}</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<button id="ok" onClick="JavaScript:Good()"><li class="fa fa-thumbs-o-up" style="color:red" > <span id="okspan"> ( ${DATA.ok} )</span></button>
					
  				<button id="cancel" onClick="JavaScript:Bad()"><li class="fa fa-thumbs-o-down" style="color:red" > <span id="cancelspan"> ( ${DATA.bed} )</span></button>

				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a href="JavaScript:List()">목록보기</a>
					<a href="JavaScript:Reple()">답글달기</a>
					
<%--		  	<c:if test=" ${DATA.writer eq sessionScope.ID}"> --%>      								
					<a  id="modify" href="javaScript:Modify()">수정하기</a>
					<a   id="delete"   href="javaScript:Delete()">삭제하기</a>
<%--			</c:if>  --%>   
					
				</td>
			</tr>
		</table>
	
	</body>
</html>

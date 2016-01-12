<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
}

li {
    display: inline;
    color:green;
    cursor:pointer;
}
</style>
	<%	   
	String	tempID = (String) session.getAttribute("id");
	if(tempID == null || tempID.length() == 0) {
		
		String url =request.getRequestURL().toString();
		
		session.setAttribute("url", url );
	/*	
		RequestDispatcher rd =
				  request.getRequestDispatcher("Member/LoginForm.jsp");
		rd.forward(request,response);
		*/
						 
		response.sendRedirect("../memberservelet/login");			
	} 
	%>
	
<script>
   function notify()
   {
	   location.href ="../notifyboardservelet/list"   
   }
   
   function freeboard()
   {
	   location.href ="../freeboardservelet/list"   
   }
   function logout()
   {
	   location.href ="../member/LogoutProc.jsp" 	   
   }
   
   function modify()
   {	   
	   location.href ="../memberservelet/modify"
   }
 
</script>

<div>
<ul>
<li onclick="notify()">공지사항 </li>
<li onclick="freeboard()">자유게시판</li>

<li style="float:right"  onclick="modify()"> 개인정보 수정</li>
<li  style="float:right"  onclick="logout()" >로그 아웃</li>
<li style="float:right"  > <%=(String) session.getAttribute("name") %> 님 환영합니다.</li>
</ul>
</div>

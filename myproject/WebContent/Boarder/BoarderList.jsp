<%@page import="iedu.dao.BoardDao"%>
<%@page import="iedu.dao.BoardSql"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
function onwrite() {
	
	location.href = "../Board/BoardWrite.bbs";
}

function detailview(no ) {
	location.href = "../Board/BoarderDetail.bbs?oriNo="+no;
	
}
</script>
</head>
<body>
<%

 ArrayList<BoardDao> array = (ArrayList<BoardDao>)request.getAttribute("Data"); 
%>
    <table border="1" width="80%" align="center" >
     <tr>
      <th>번호</th>
      <th>제목</th>
      <th>글쓴이</th>
      <th>작성일</th>
      <th>조회수</th>
     <tr>
     <%for( BoardDao data  :array ) { %>
    <tr onclick="detailview('<%=data.no %>'); " style="cursor:pointer;">
    
    <td><%=data.no %> </td>
    <td><%=data.title %> </td>
    <td><%=data.write %> </td>
    <td><%=data.date %> </td>
    <td><%=data.hit %> </td>
    
    </tr>
    
      <% } %>
      <tr>
      <td align="center" colspan="5">
 					<a href="../Board/BoardInsertForm.bbs">글쓰기</a>
      </td>
      </tr>
    </table>



</body>
</html>
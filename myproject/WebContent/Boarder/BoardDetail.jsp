
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="iedu.dao.BoardDao"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
</head>

<%
    BoardDao board = (BoardDao)  request.getAttribute("SETDATA");
%>
<body>
  <!--   
    선택한 게시물의 상세보기 내용을 출력하고 
   -->
   <table width="60%" board="1'" align="center">
        <tr>
         <td>글번호
            <%= board.no%>
         </td>
         <td></td>
         <td>조회수
         </td>
         <td>
            <%= board.hit%>
         </td>
        </tr>
        
          <tr>
         <td>작성자
         </td>
         <td>
           <%= board.write%>
         </td>
         <td>작성일 
         </td>
         <td>
           <%= board.date%>
         </td>
        </tr>
        <tr>
        <td >
             제목          
        </td>
        <td colspan="3">
            <%=board.title %>
        </td>
        </tr>
        <tr>
        <td>
             내용         
        </td>
        </tr>
        <tr>
        <td colspan="3">
              <%=board.content %>        
        </td>
        </tr>        
        <tr>
         <td colspan="4"> 
        
        </tr>
   </table>
  

</body>
</html>
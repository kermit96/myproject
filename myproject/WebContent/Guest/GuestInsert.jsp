<%@page import="iedu.dao.GuestDao"%>
<%@page import="iedu.dao.LoginDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<head>

<%

        String content = request.getParameter("content");
        String id = (String)session.getValue("id");
       
        GuestDao dao= new GuestDao();
        
        dao.insertGuest(id, content);         
        response.sendRedirect("GuestList.jsp");
       
%>

</head>

<body>


</body>
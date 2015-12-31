<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 이제  이 jsp 문서에 JSTL를 사용할 예정이다. --%>
<%-- 
          <%==  를 대신 할수 있는 것 
    
      --%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery.min.js"> </script>
<%
         // 원래는 이 부분은 Model이 만들어서 제공해야 하는데 ....
         //  모델이 해야 할 일을 여기서 한다고 가정하고 작업을 진행한다. 
         
         String name="Hong Gil Dong";
         request.setAttribute("name",name);              
         request.setAttribute("age",24);
         
         String[] names = {"홍길동","박아지","장독대","장독대1" ,"장독대2","장독대3"  };
         
         request.setAttribute("names", names);
%>

</head>
<body>
	<%--
    JSTL을 이용해서 모델이 생산 데이터를 이용해 보자 
     1. 일반적인 출력 
 --%>
	<c:set var="myval" value="${'vv'}" />

	당신의 이름은 ${name} 입니다.
	<br> 당신의 나이는 ${age} 입니다. ,
	<br> 당신의 나이는 정말로
	<c:out value="${age}" />
	입니다.
	<br> 당신의 나이는 정말로2
	<c:out value="${myval}" />
	입니다.
	<br>
	<c:set var="count" value="${1}" />
	<c:set var="count" value="${count+1}" />
	현재 카운터는 ${count } 입니다.
	<br>
	<c:remove var="count" />
	현재 카운터는 ${count } 입니다.
	<br>
	<table width="50%" align="center" border="1">


		<c:forEach var="a" begin="1" end="6">
			<c:if test="${a %2 eq  0}">
				<tr bgcolor="green">
			</c:if>
			<c:if test="${a %2 eq  1}">
				<tr bgcolor="red">
			</c:if>

			<td>데이타</td>

			<c:forEach var="item" items="${names }">
				<td>${item}</td>
			</c:forEach>

			</tr>
		</c:forEach>
	</table>


</body>
</html>
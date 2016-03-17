<%@ page language="java" contentType="text/plane; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
	오늘은
		{"gugun" : ["종로구", "구로구", "강남구", .....]}
--%>
{"gugun" : [
	<c:forEach var="temp" items="${GUGUN}" varStatus="st">
		<c:if test="${st.last}">
			"${temp}"
		</c:if>
		<c:if test="${not st.last}">
			"${temp}",
		</c:if>
	</c:forEach>
]}

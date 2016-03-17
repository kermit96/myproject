<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
	<codes>
		<code>
			<zip>우편번호</zip>
			<addr>시도 구군 동 도로명 빌딩이름</addr>
		</code>
		<code>
			<zip>우편번호</zip>
			<addr>시도 구군 동 도로명 빌딩이름</addr>
		</code>
		<code>
			<zip>우편번호</zip>
			<addr>시도 구군 동 도로명 빌딩이름</addr>
		</code>
	</codes>
--%>
<codes>
	<c:forEach var="temp"	items="${ZIP}">
		<code>
			<zip>${temp.ZIPCODE}</zip>
			<addr>${temp.SIDO} ${temp.GUGUN} ${temp.DONG} ${temp.ROAD} ${temp.BLG} ${temp.JIBUN}</addr>
		</code>
	</c:forEach>
</codes>

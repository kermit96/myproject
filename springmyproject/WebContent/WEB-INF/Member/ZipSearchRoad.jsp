<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	이번 문서는 
	<dongs>
		<dong>구기동</dong>
		<dong>부암동</dong>
		<dong>신영동</dong>
		<dong>평창동</dong>
	</dongs>
--%>
<roads>
	<c:forEach var="temp" items="${ROAD}">
		<road>${temp}</road>
	</c:forEach>
</roads>

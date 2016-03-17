<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%--	
		���� �� JSP ������ JSTL�� ����� �����̴�.
		�׷��� �� JSP ������ JSTL�� ����ϱ� ���� �±� ���̺귯�� ������ ���־�� �Ѵ�.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
<%
	//	������ �� �κ��� Model�� ���� �����ؾ� �ϴµ�.......
	//	���� �ؾ� �� ���� ���⼭ �Ѵٰ� �����ϰ� �۾��� �����Ѵ�.	
	String		name = "Hong Gil Dong";	
	//	���� ������ �����͸� �信�� ��ٰ� ��������.
	request.setAttribute("name", name);
	request.setAttribute("age", 24);
	
	String[] names = {"ȫ�浿", "�ھ���", "�嵶��", "�̱���", "������"};	
	request.setAttribute("names", names);
	
	ArrayList	list = new ArrayList();
	list.add("�¿�");
	list.add("����");
	list.add("���");
	list.add("Ƽ�Ĵ�");
	list.add("����");
	list.add("����");
	list.add("ȿ��");
	list.add("����ī");
	list.add("����");
	
	request.setAttribute("LIST", list);
	
	String	imsi = "ȫ�浿 �ھ��� �嵶�� �̱��� ������";
	request.setAttribute("IMSI", imsi);	
	
	HashMap	map = new HashMap();
	map.put("IRUM", "�屹��");
	map.put("NAI", 24);
	
	request.setAttribute("MAP", map);
	
%>
	<body>
<%--
	JSTL�� �̿��ؼ� ���� ���� �����͸� �̿��� ����
	1.	�Ϲ����� ���
--%>
		����� �����ϴ� ����� �̸��� ${MAP.IRUM} �̱���<br>
		�� ����� ���̴� ${MAP.NAI} �Դϴ�.<br>

		 ����� �̸��� ������ <c:out value="${name}"  /> �Դϴ�.<br>
		 ����� ���̴�  ������ <c:out value="${age * 2 }" /> �̱���<br>
		 
		 <c:set var="temp" value="${'���ȯ'}" />
<%--	�ڹٷ� ���ϸ� String temp = "���ȯ"		�� �����ϰ� ó���ȴ�. --%>
		���� �̸��� ${temp} �̳׿�<br>
		 <c:set var = "count" value="${1}" />
<%--	���� ������ �ϴ� ����� ������ ����. --%>
		 <c:set var = "count" value="${count + 1 }" />
		���� ī���ʹ� ${count} �Դϴ�.<br>
		
		<c:remove var="count" />
		���� ī���ʹ� ${count} �Դϴ�.<br>

		<table width="50%" align="center" border="1">
		<c:forEach var="a" begin="1" end="5">
			<c:if test="${a % 2 eq 0}">
			<tr bgcolor="red">
				<td>������</td>
			</tr>
			</c:if>
			<c:if test="${a % 2 eq 1}">
			<tr bgcolor="yellow">
				<td>������</td>
			</tr>
			</c:if>
		</c:forEach>
		</table><br>

		<table width="50%" align="center" border="1">
		<c:forEach var="a" begin="1" end="5">
			<c:choose>
				<c:when test="${a % 2 eq 0}">			
			<tr bgcolor="blue">
				<td>������</td>
			</tr>
				</c:when>
				<c:otherwise>
			<tr bgcolor="pink">
				<td>������</td>
			</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</table><br>

		<table width="50%" border='1' align="center">
			<c:forEach var="item" items="${names}">
<%--	���� �迭�� �ִ� ������ �ϳ��� ���ͼ� ������ ����(item)�� ���Ǹ鼭
		�ݺ��� ���̴�.
 --%>			
 				<tr>
 					<td>${item}</td>
 				</tr>
			</c:forEach>
		</table><br>
		
		<table width="50%" border="1" align="center">
			<c:forEach var="temp" items="${LIST}" varStatus="sta">
<%--	�÷��ǿ� �ִ� �����Ͱ� �ϳ��� ���ͼ� temp�� ���Ǿ �ݺ��ϰ�
		���� �����Ͱ� ���ͼ�  temp�� ���Ǿ �ݺ��ϴ� �������� ��� �ݺ��Ѵ�.
 --%>
 			<c:if test="${sta.index % 2 eq 0}">
			<tr bgcolor="green">
				<td>${temp}</td>
			</tr>
			</c:if>
			<c:if test="${sta.index % 2 eq 1}">
			<tr bgcolor="gold">
				<td>${temp}</td>
			</tr>
			</c:if>
			</c:forEach>
		</table><br>

		<table width="50%" border="1" align="center">
			<c:forEach var="temp" items="${LIST}" varStatus="sta">
<%--	����	ù��°�� �������� �׸����� ĥ�ϰ� �� �ȿ� �������� gold�� ĥ�϶�. --%>
			<c:if test="${sta.first or sta.last}">
			<tr bgcolor="green">
				<td>${temp}</td>
			</tr>
			</c:if>
			<c:if test="${(not sta.first) and (not sta.last)}">
			<tr bgcolor="gold">
				<td>${temp}</td>
			</tr>
			</c:if>
			</c:forEach>
		</table><br>
		
		<c:forTokens var="temp" items="${IMSI}" delims=" ">
			${temp}<br>
		</c:forTokens>



	</body>
</html>










<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
	����� ������ �ִ� ��Ű�� ������ �����ϴ�.<br>
<%
	//	�ʿ��ϸ� ��Ű�� ������ �ͼ� Ȯ������.
	Cookie[]		cooks = request.getCookies();
	for(int i = 0; i < cooks.length; i++) {
		String	key = cooks[i].getName();
		String	value = cooks[i].getValue();
		out.println(key + " : " + value + "<br>");
	}
%>
	</body>
</html>

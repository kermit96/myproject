<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	POST ������� �ѱ��� �Ѿ���� ���� ������ �ȴ�.
	//	�̰��� ���� �ϱ� ���ؼ��� �����͸� �޴� ��������
	request.setCharacterEncoding("EUC-KR");
	//	�� ����ؼ� �޾ƾ� �Ѵ�.

	//	���� �ִ� �����Ͱ� ������ ���޵Ǿ����� ������
	//		request.getParameter("");	�� ������ �ȴ�.
	//	�̶� ���ǻ���
	//		""	�ȿ���		�ް���� �Է¿�ҿ� ���� name �Ӽ����� ���� �Ѵ�.
	String		name = request.getParameter("irum");
	String		gender = request.getParameter("gender");
	String		h1 = request.getParameter("hobby1");
	String		h2 = request.getParameter("hobby2");
	String		h3 = request.getParameter("hobby3");
%>
	����� �̸��� <%= name %> �̱���<br>
	����� ������ <%= gender %>�̱���<br>
	����� ��̴� <%= h1 + " " + h2 + " " + h3 %>�̱���
	</body>
</html>














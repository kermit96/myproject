<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	���θ����� ������ �����ߴٰ� �����ϰ� �� ������ ������ ��Ű�� Ŭ���̾�Ʈ���� ��������
	//	1.	��Ű�� �����.
	Cookie		cook1 = new Cookie("ONE", "Ondol Mate");
	Cookie		cook2 = new Cookie("TWO", "Cicle");
	Cookie		cook3 = new Cookie("THREE", "Dambae");

	//	2.	��Ű�� Ŭ���̾�Ʈ���� �����Ѵ�.
	response.addCookie(cook1);
	response.addCookie(cook2);
	response.addCookie(cook3);
%>
<%--	������ ����� �������� --%>
		��Ű�� ���� �����߽��ϴ�.
	</body>
</html>











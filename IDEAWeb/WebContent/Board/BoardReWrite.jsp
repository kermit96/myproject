<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	�𵨿��� �˷��� �����͸� �˾Ƴ���.
	int		oriNO = (Integer) request.getAttribute("ORINO");
	//	�����ٰ��� ����
	//	�ٽ� �󼼺���� ������ �ȴ�.
	response.sendRedirect("../Board/BoardDetail.bbs?oriNO=" + oriNO);
	//	�信�� ����� �����Ͱ� ������ �װ��� MVC�� ������ ���� �𵨿��� �����ؾ� �Ѵ�.
	
	//	����		GET ��Ŀ��� �Ķ���͸� ������ ?�������� ����� ������ �־�� �ȵȴ�.
	//	��>		?oriNO = 10				�̷��� ���� �ȵȴ�.
	//				?oriNO=10					�̷��� ��� �Ѵ�.
	//				?no=10 & name=hong	�̷��� ���� �ȵȴ�.
	//				?no=10&name=hong		�̷��� ��� �Ѵ�.
%>
	</body>
</html>








<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	1.	���ؽ�Ʈ �н��� �˾Ƴ���.
	//		���ؽ�Ʈ �н���?	Ŭ���̾�Ʈ�� � ������� ��û�� �ߴ����� �˾Ƴ��� ��
	String		path = request.getContextPath();
	out.println("���ؽ�Ʈ �н� = " + path + "<br>");
	//	2.	��û ����� �˾Ƴ���.
	String		method = request.getMethod();
	out.println("��û ��� = " + method + "<br>");
	
	//	����
	//		Ŭ���̾�Ʈ�� ��ȣ�� �˷��ָ� �ش� ��ȣ�� ���� ����� �̸��� ����ϴ� ������ ������
	
	//	9����� �̸��� ����ϰ� �ִ�.
	//		�������� �����ͺ��̽��� 9���� �����Ͱ� �ִٰ� ������ �޶�
	String[]	names = {"�¿�", "���", "����", "ȿ��", "����", "Ƽ�Ĵ�", "����ī", "����", "����"};
	//		����ڰ� ��ȣ�� �˷��ֱ�� ����ߴµ�			?num=$
	//	Ŭ���̾�Ʈ�� �˷��� ��ȣ�� �˾Ƴ���.
//	String	strNum = request.getParameter("num");
//	int		num = Integer.parseInt(strNum);
	
/*	Enumeration	enm = request.getParameterNames();
	
	while(enm.hasMoreElements()) {
		String	key = (String) enm.nextElement();
		out.println(key + "<br>");
	}
*/	
	String[] name = request.getParameterValues("num");		//	"1"
	for(int i = 0; i < name.length; i++) {
		int	num = Integer.parseInt(name[i]);
		out.println("������� �ҳ�ô� ��� = " + names[num] + " �̱���<br>");
	}
	//		�����ͺ��̽� �߿��� ������� �Խù��� ��ȣ�� �˷��ָ� �� �Խù��� �����ִ� ���̴�. 
%>
	</body>
</html>







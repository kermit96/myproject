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
	Hashtable	map = new Hashtable();
	map.put("isundol", "1234");
	map.put("nasundol", "1234");
	map.put("goldgii", "1234");
	
	//	����
	//		1.	Ŭ���̾�Ʈ�� �˷��� id, ����� �˾Ƴ���.
	String	id = request.getParameter("id");
	String	pw = request.getParameter("pw");
	
	//		2.	���� map �ȿ� �׷� ���̵� �ִ��� Ȯ���Ѵ�.
	if(map.containsKey(id)) {
		//	3.	�� ���̵� �ش��ϴ� ��й�ȣ�� ��ġ�ϴ��� Ȯ���Ѵ�.
		String		mapPw = (String) map.get(id);
		if(mapPw.equals(pw)) {
			//	������� ����� ����� ȸ������ Ȯ���ϴ�.
			//	�ٽ�(�١١�)
			//	�� Ŭ���̾�Ʈ�� ������ ���ǿ� ����� ���´�.
			//	������ �� Ŭ���̾�Ʈ�� id�� ���ǿ� ����� ������ �Ѵ�.
			session.setMaxInactiveInterval(60);
			//	����	�ð� ������ �� ������ �Է��Ѵ�.
			session.setAttribute("CID", id);
		}
	}
	//	���� ����� ������ �����̴�.
	//	JSP�� �׻� ���� ����� Ŭ���̾�Ʈ�� ������ �־�� �Ѵ�.
	//	��Ģ������ JSP�� ������ ó�������� �� ����� Ŭ���̾�Ʈ���� �˷��־�� �Ѵ�.
	//	������ ��κ��� ��� �α��� �Ŀ��� �ٽ� ���� ȭ������ ������ �ϰ� �Ǵ���
	//	�� ���� ������ ������ �ϴ� ��ſ� ���� ȭ������ �����ϵ��� ��û�� �ٲ۰��̴�.
	//	������ �ٸ� ������ �ϵ��� �ϴ� ����
	//	�츮�� Redirect ��� �θ���
	//	�̰��� �����ϰ� �ϴ� ���			response.sendRedirect() �̴�.
	response.sendRedirect("LoginForm.jsp");
	//	�̷��� ����� ������ ������ �ٽ� LoginForm.jsp�� �����ؼ� �� ����� �����ϰ� �ȴ�. 
%>
	</body>
</html>









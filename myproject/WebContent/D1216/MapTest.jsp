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
	HashMap	map = new HashMap();
	map.put("ȫ�浿", "����ó? ������ ���� �ֱ������� �����̸� ���� ���Ƿ� �������� ���� ������ ������ �����ΰ�? �ֱ������� �±رⰡ ����Ѵ�. �� ���� �ѵ��!");
	map.put("�ھ���", "������ļպе��̳� ����� ì�ܶ�.. ģ�����ļյ� ��������� �ļ����� ������������..");
	map.put("�嵶��", "����ó ���, ���� ����̳� �� ������, �����Ѱ� �ڽŵ��� ������ ������ �� �±ر� ������� ��ġ���̳�? �ֱ����� �ڿ������� �췯���� ����, �ùε��� �̿��ϴ� ���忡 ���� �±ر� �Ծ�븦 �� ���峪. ��ģ �͵�");
	map.put("�̱���", "����ó�� ���������ڿ� ���� ������� �����. ���� ���µ� �ð� ����, ���� ���� ���� ����. ���� ���� ���� ��ġ�鼭 ���� ���� ���� ����� �е鿡�Դ� ������� �ϴ� ����ó ����� �ִ°�?");
	map.put("������", "����ü �츮����� �� ���� ���� ���� �ϴ� �ڵ��� �� �̸� ������...");

	String	key = request.getParameter("name");
	String	data = (String) map.get(key);
%>
		<dl>
			<dt><%= key %>	</dt>
			<dd><%= data %></dd>
		</dl>
	</body>
</html>

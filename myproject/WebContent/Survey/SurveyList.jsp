<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%--	�������� ���׺��� ������ --%>
		<form method="POST" action="SurveyInsert.jsp">
			<table width="70%" border="1" align="center">
				<tr>
					<td colspan="2" align="center">
						���󿡼� ���� �̻� ������� ũ���������� ���� ������ ���� ����� �����Դϱ�?
					</td>
				</tr>
				<tr>
					<td>1. <input type="radio" name="dap" value="s_dap1" checked></td>
					<td>�¿�</td>
				</tr>		
				<tr>
					<td>2. <input type="radio" name="dap" value="s_dap2"></td>
					<td>������</td>
				</tr>		
				<tr>
					<td>3. <input type="radio" name="dap" value="s_dap3"></td>
					<td>�Ͽ���</td>
				</tr>		
				<tr>
					<td>4. <input type="radio" name="dap" value="s_dap4"></td>
					<td>����</td>
				</tr>		
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="���� �����ϱ�">
<%--
	�츮�� ���� ��� �������� ������ ��ũ ������� ����ڰ� ������ �� �ֵ��� �غ� ���־�� �Ѵ�.
	�ּҸ� ġ�� ���� �ϴ� ������ ����� �Ѵ�.
 --%>						
 						<a href="SurveyResult.jsp">�������</a>
					</td>
				</tr>		
			</table>
		</form>
	</body>
</html>







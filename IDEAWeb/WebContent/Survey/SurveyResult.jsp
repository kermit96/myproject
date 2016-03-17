<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<style>
			a {text-decoration: none;}
			a:link {
				color: #FF0000;
				
			}
			a:visited {
				color: #00FF00;
			}
			a:hover {
				color: #FF00FF;
			}
			a:active {
				color: #0000FF;
			}
			body {
				font-size: 12px;
			}
			table.out {
				border-collapse: collapse;
				width: 70%;
				margin : auto;
				border-bottom: 1px solid MidnightBlue  ;
				border-top: 1px solid MidnightBlue  ;
			}
			table.out td {
				padding: 10px;
				text-align: center;
			}
			table.in th.centettd {
				padding: 10px;
				border-bottom: 1px solid PaleGreen ;
				border-top: 1px solid PaleGreen ;
				text-align:center;
			}
			table.in {
				border-collapse: collapse;
				width: 90%;
				margin : auto;
			}
			table.in td.in {
				border-bottom: 1px solid PaleGreen ;
				border-top: 1px solid PaleGreen ;
				text-align:left;
			}
		</style>
	</head>
	<body>
<%--	��� ���� ���� --%>
<%
	//	����
	//		���� �����ͺ��̽� �ִ� ������ �˾Ƴ���.
	Connection	con = null;
	Statement	stmt = null;
	ResultSet		rs = null;
	//	JSP ���� �ȿ����� try �۾��� ���� �ʾƵ� �����ϴ�.
	//	�ֳ��ϸ� JSP ������ �ڵ������� try �۾��� �� ���ұ� �����̴�.
	//	������ �׷��ٰ�  try �� ���� ������ ���ܰ� �߻��Ҷ� ������ ������ �ľ��ϱⰡ ��ƴ�.
	//	�׷��Ƿ� �ʿ��ϴٸ� ����ó��  try ó���� ���ִ� ���� ����.

	int[]	daps = new int[4];			//	������ �׸� �ִ� �����͸� ����� ����
	try {
			//	����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	���ؼ�
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.81:1521:orcl", 
														"scott", "tiger");
			//	������Ʈ��Ʈ
			stmt = con.createStatement();
			//	����
			String	sql = "SELECT * FROM Survey";
			rs = stmt.executeQuery(sql);
			rs.next();
			daps[0] = rs.getInt("s_dap1");
			daps[1] = rs.getInt("s_dap2");
			daps[2] = rs.getInt("s_dap3");
			daps[3] = rs.getInt("s_dap4");
	}
	catch(Exception e) {
		System.out.println(e);
	}
	finally {
		rs.close();
		stmt.close();
		con.close();
	}
	//		�̰��� �̿��ؼ� ������ �׸��� ������� ����Ѵ�.
	int		total = 0;
	for(int i = 0; i < daps.length; i++) {
		total = total + daps[i];
	}
	//	���� �Ҽ��� ���ϴ� ���� �������� int�� ��Ҵ�.
	int[]		per = new int[4];			//	������ �׸��� ������� ����� ����	
	for(int i = 0; i < daps.length; i++) {
		per[i] = (int)(daps[i] * 100 / total);
	}
	//		�� ������� �̿��ؼ� ����� �����ش�.
	
	String[]	ex = {"�¿�", "������", "�Ͽ���", "����"};
	
%>
	<table class="out">
		<tr>
			<td>
				<table class="in">
					<tr>
						<th colspan="3" class="centettd">
							���󿡼� ���� �̻� ������� ũ���������� ���� ������ ���� ����� �����Դϱ�?
						</th>
					</tr>
<%
		for(int i = 0; i < per.length; i++) {
%>
					<tr>
						<td width="15%" class="in"><%=(i + 1) + " " + ex[i] %></td>
						<td width="70%" class="in">
<%--	�׷����� �׸��� ���(���� �������� ���) 
		������ ������ŭ �׸��� ������ �����ϸ� �׷��� ó�� ���� ���̴�.
--%>			
<%				for(int j = 0; j < per[i]; j++) { %><img src="../images/<%= (i + 1) %>.png"><% } %>
						</td>
						<td width="15%" class="in"><%= per[i] %> %</td>
					</tr>
<%
		}
%>	
					<tr>
						<th class="centettd" colspan="3">
							<a href="SurveyList.jsp">���� ���� �׸� ����</a>
						</th>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</body>
</html>





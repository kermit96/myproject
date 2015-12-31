<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
			table.in td.in{
				border-bottom: 1px solid PaleGreen ;
				border-top: 1px solid PaleGreen ;
				text-align:left;
				padding: 10px;
			}
		</style>
	</head>
	<script>
		function send() {
			var	frm = document.getElementById("frm");
			frm.submit();
		}	
	</script>
	<body>
<%--	�������� ���׺��� ������ --%>
		<form method="POST" action="SurveyInsert.jsp" id="frm">
			<table class="out">
				<tr>
					<td>
						<table class="in">
							<tr>
								<th colspan="2" class="centettd">
									���󿡼� ���� �̻� ������� ũ���������� ���� ������ ���� ����� �����Դϱ�?
								</th>
							</tr>
							<tr>
								<td class="in" width="20%">1. <input type="radio" name="dap" value="s_dap1" checked></td>
								<td class="in" width="80%">�¿�</td>
							</tr>
							<tr>
								<td class="in">2. <input type="radio" name="dap" value="s_dap2"></td>
								<td class="in">������</td>
							</tr>
							<tr>
								<td class="in">3. <input type="radio" name="dap" value="s_dap3"></td>
								<td class="in">�Ͽ���</td>
							</tr>
							<tr>
								<td class="in">4. <input type="radio" name="dap" value="s_dap4"></td>
								<td class="in">����</td>
							</tr>		
							<tr>
								<th colspan="2" align="center" class="centettd">
								<%--
									<input type="submit" value="���� �����ϱ�">
								 --%>
								 	<a href="JavaScript:send()">�����ϱ�</a>
<%--
	�츮�� ���� ��� �������� ������ ��ũ ������� ����ڰ� ������ �� �ֵ��� �غ� ���־�� �Ѵ�.
	�ּҸ� ġ�� ���� �ϴ� ������ ����� �Ѵ�.
 --%>						
 									<a href="SurveyResult.jsp">�������</a>
								</th>
							</tr>		
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>







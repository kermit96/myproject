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
<%--	설문조사 문항보기 페이지 --%>
		<form method="POST" action="SurveyInsert.jsp" id="frm">
			<table class="out">
				<tr>
					<td>
						<table class="in">
							<tr>
								<th colspan="2" class="centettd">
									세상에서 가장 이쁜 사람으로 크리스마스를 같이 보내고 싶은 사람은 누구입니까?
								</th>
							</tr>
							<tr>
								<td class="in" width="20%">1. <input type="radio" name="dap" value="s_dap1" checked></td>
								<td class="in" width="80%">태연</td>
							</tr>
							<tr>
								<td class="in">2. <input type="radio" name="dap" value="s_dap2"></td>
								<td class="in">한지민</td>
							</tr>
							<tr>
								<td class="in">3. <input type="radio" name="dap" value="s_dap3"></td>
								<td class="in">하연수</td>
							</tr>
							<tr>
								<td class="in">4. <input type="radio" name="dap" value="s_dap4"></td>
								<td class="in">김사랑</td>
							</tr>		
							<tr>
								<th colspan="2" align="center" class="centettd">
								<%--
									<input type="submit" value="설문 참여하기">
								 --%>
								 	<a href="JavaScript:send()">참여하기</a>
<%--
	우리가 만든 모든 페이지는 하이퍼 링크 방식으로 사용자가 접근할 수 있도록 준비를 해주어야 한다.
	주소를 치고 들어가야 하는 문제는 없어야 한다.
 --%>						
 									<a href="SurveyResult.jsp">결과보기</a>
								</th>
							</tr>		
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>







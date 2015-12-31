<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<style>
			body {font-size:12px;}
			ul {
				list-style-type: none;
				margin: 0;
				padding: 0;
				width: 20%;
				position: fixed;
				height: 100%;
				overflow: auto;
				background-color: Khaki ;
			}
			li a {
				display: block;
				color: #000;
				padding: 8px 0 8px 16px;
				text-decoration: none;
			}
			li a.active {
				background-color: DarkRed;
				color: white;
			}
			li a:hover:not(.active) {
				background-color: black;
				color: white;
			}
		</style>
	</head>
	<body>
<%--	�������� �������� ������ ��ũ�� �� �ֵ��� ���ش�. --%>	
<ul>	
	<li><a class="active" href="#">My Menu</a></li>
	<li><a href="http://localhost:8080/IDEAWeb/Member/LoginForm.jsp">�α���</a><li>
	<li><a href="http://localhost:8080/IDEAWeb/Survey/SurveyList.jsp">��������</a><li>
	<li><a href="http://localhost:8080/IDEAWeb/Guest/GuestList.jsp">����</a><li>
	<li><a href="http://localhost:8080/IDEAWeb/Board/BoardList.bbs">��� �Խ���</a><li>
</ul>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
		<h1 style="text-align:center">ISUNDOL HOME</h1>
</div>
	</body>
</html>




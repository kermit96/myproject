<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function ReqAjax() {
				//	���� �� �Լ��� ����Ǵ� ���� ajax ����� �̿��ؼ� ������ ��û�� �Ѵ�.
				$.ajax({
					url:"JsonMapResp.jsp",
					dataType:"json",
					type:"GET",
					success:function(data) {
						var	h = "<table width='50%' border='1` align='center'>";
						h += "<tr>";
						h += "<td>";
						h += data.�̸�;
						h += "</td>";
						h += "<td>";
						h += data.����;
						h += "</td>";
						h += "</tr>";
						h += "<tr>";
						h += "<td>";
						h += data.����;
						h += "</td>";
						h += "<td>";
						h += data.����;
						h += "</td>";
						h += "</tr>";
						h += "</table>";
						//	��ó�� Ajax�� ���� ���(������)�� �̿��ؼ� �ڹ� ��ũ��Ʈ��
						//	�ʿ��� ���� ���� ����ϴ� ���� ��Ģ�̴�.
						$("#div1").html(h);
					},
					error:function(){
						alert("������ �ȵǿ�");
					}
				});
			}
		</script>
	</head>
	<body>
<%--	���� ���� ����� ����� ��ġ�� ����ְ�.... --%>	
		<div id="div1"></div>
		<a href="JavaScript:ReqAjax()">����</a>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function Send() {
				$.ajax({
					url:"JsonMixResp.jsp",
					dataType:"json",
					type:"GET",
					//	ajax�� �ѹ� ��û�� ����� ������ ��û�� �ٽ� ������
					//	������ �������� �ʴ� ��쵵 �����.
					//	�� ������ �ذ��ϴ� �����
					//	��û�� �ٲٴ� ����� �ִµ�....
					//	��û�� �ٲ� �� �����Ƿ� �Ķ���͸� �ٲ㼭 ��û�ϸ� �ȴ�.
					data : "temp=" + new Date(), 					
					//	�����ϸ� ����� �Լ��� ���� ���� ����� �� �ִ�.
					//	�� � �Լ��� ��������� ����� �ָ� �ȴ�.
					success: ShowData,
					error:function(){
						alert("������ �ȵǿ�");
					}
				});
			}
			//	�����ϸ� ����� �Լ��̴�.
			function ShowData(data) {
				//	1.	���� ũ�Դ� �� �����̹Ƿ� �� �ȿ� �����͸� ������.
				var	friends = data.kimfriend;
				//	�̰��� ����� �迭�� �� ���̴�.
				//	������ �迭 ���·� ������ �Ѵ�.
				var	h = "<table border='1' align='center' width='70%'>";
				$.each(friends, function(index){
					var	friend = friends[index];
					//	�̰��� �ٽ� ������ ������ �ȴ�.
					var	imsi = friend.Friend;
					//	�̰͵� �ٽ� ���̴�.
					var	name = imsi.name;
					var	tel = imsi.phone;
					var	addr = imsi.addr;
					var	age = imsi.age;
					h += "<tr>";
					h += "<td>" + name + "</td>";
					h += "<td>" + tel + "</td>";
					h += "<td>" + addr + "</td>";
					h += "<td>" + age + "</td>";
					h += "</tr>";
				});
				h += "</table>";				

				$("#div1").html(h);
			}
		</script>
	</head>
	<body>
		<div id="div1"></div>
		<input type="button" value="����" onClick="JavaScript:Send()">
	</body>
</html>
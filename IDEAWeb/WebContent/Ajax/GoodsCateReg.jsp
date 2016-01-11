<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="jdbc.*, java.sql.*, java.util.*"	%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script>
			function LREG() {
				var	frm = document.getElementById("frm");
				frm.action = "CateLargeInsert.jsp";
				frm.submit();
			}
			function MREG() {
				var	frm = document.getElementById("frm");
				frm.action = "CateMiddleInsert.jsp";
				frm.submit();
			}
			function getMCate() {
				//	�޺����ڸ� �˾Ƴ���.
				var	combo = document.getElementById("lcate");
				//	�� �޺����ڿ��� ���° �׸��� ���õǾ������� �˾Ƴ���.
				var	index = combo.selectedIndex;
				if(index == 0) {
					return;
				}
				//	������ �׸��� ������ �˾Ƴ���.
				var	value = combo[index].value;
				//	���� �� �����͸� �ָ鼭 �� ī�װ��� �˾ƴ޶�� Ajax�� ��Ź�Ѵ�.
				
				$.ajax({
					url : "CateMiddleSelect.jsp",
					data : {lcate:value},
					dataType : "xml",
					type : "GET",
					cache:false,
					success : setCombo, 
					error : function(err) {
						alert("������ �ȵǴµ� �� ���Դ�?==>"+err);
					}
				});
			}
			function setCombo(data) {
				//	����
				//		1.	�޺��� ������ ������ �����.
				//			1)	�޺����ڸ� �˾Ƴ���
				
				/*
				var	combo = document.getElementById("mcate");
				var	len = combo.length;
				//			2)	�� ������ŭ �ݺ��ϸ鼭 �����.
				for(i = 0; i < len; i++) {
					combo.remove(0);
				) */
				
				$("#mcate").empty();
				
				//	������ == �����ϼ��並 ���δ�.
		/*		
				var	option = document.createElement("option");
				option.text = "== �����ϼ��� ==";
				option.value = "0";
				combo.options.add(option);
				*/
				 
				 var option = "<option value=\"0\"> == �����ϼ��� == </option>";
				 
				 $("#mcate").append(option);
			
				// xml ��� 
				
				$mcate =$(data).find("Goods");
				
				$goods = $mcate.find("Good");
				
		

				$goods.each(function() {
					
					var name=$(this).find("NAME").text();
					var code=$(this).find("CODE").text();;
				
					/*
					var	option = document.createElement("option");
					option.text = name;
					option.value = code;
					
					combo.options.add(option);
					*/
					
					var option = "<option value=\""+ code +"\">"  +name   +"</option>";
		
					 $("#mcate").append(option);
				
					
				}
						
				);
				
				
				
/*
			
				
				
				for(var index in data) 
				{
					
					var code = data[index].CODE;
					var name = data[index].NAME;
					
					var	option = document.createElement("option");
					option.text = name;
					option.value = code;
					
					combo.options.add(option);
					
				}
				*/
				
			/*	
				//		2.	�޺��� ���ο� �������� ä���.
				//		������� �����͸� �̿��ؼ� ������ ���� ä���.
				$.each(data, function(index){
					var code = data[index].CODE;
					var name = data[index].NAME;
					
					var	option = document.createElement("option");
					option.text = name;
					option.value = code;
					
					combo.options.add(option);
				});
		    */
			}
			</script>
	</head>
<%
	//	����
	//		���� �����ͺ��̽��� ������ �� ī�װ��� �˾Ƴ���.
	WebDB			db = null;
	Connection	con = null;
	Statement		stmt = null;
	ResultSet		rs = null;
	ArrayList		list = new ArrayList();
	try {
		db = new WebDB();
		con = db.getCON();
		stmt = db.getSTMT(con);
		//	�� ī�װ��� ������ ���Ǹ� ������.
		String	sql = "SELECT * FROM GoodsCate WHERE gc_Code LIKE '____'";
		rs = stmt.executeQuery(sql);
		//	���⼭ ���� �� ī�װ��� ArrayList�� ��� �������.
		while(rs.next()) {
			//	������ ī�װ��� �ڵ尪, �̸� �̶�� �ΰ��� �����͸� ������ �����Ƿ�....
			HashMap	map = new HashMap();
			map.put("CODE", rs.getString("gc_Code"));
			map.put("NAME", rs.getString("gc_Name"));
			
			list.add(map);
		}
	}
	catch(Exception e) {
		
	}
	finally {
		db.close(rs);
		db.close(stmt);
		db.close(con);
	}
%>
	<body>
		<form method="POST" name="frm" id="frm">
		<table border="1" align="center" width="50%">
			<tr>
				<td align="center">
					<select name="lcate" id="lcate" onChange="JavaScript:getMCate()">
						<option value="0">==�����ϼ���==</option>
<%
		for(int i = 0; i < list.size(); i++) {
			HashMap	temp = (HashMap) list.get(i);
%>
			<option value="<%= temp.get("CODE") %>"><%= temp.get("NAME") %></option>
<%
		}	
%>
					</select>
					<select name="mcate" id="mcate">
						<option value="0">==�����ϼ���==</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="lname" id="lname">
					<input type="button" value="��ī�װ� ���" onClick="JavaScript:LREG()">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="mname" id="mname">
					<input type="button" value="��ī�װ� ���" onClick="JavaScript:MREG()">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>





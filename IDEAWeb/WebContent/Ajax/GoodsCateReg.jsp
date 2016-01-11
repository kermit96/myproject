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
				//	콤보상자를 알아낸다.
				var	combo = document.getElementById("lcate");
				//	이 콤보상자에서 몇번째 항목이 선택되었는지를 알아낸다.
				var	index = combo.selectedIndex;
				if(index == 0) {
					return;
				}
				//	선택한 항목의 내용을 알아낸다.
				var	value = combo[index].value;
				//	이제 이 데이터를 주면서 중 카테고리를 알아달라고 Ajax로 부탁한다.
				
				$.ajax({
					url : "CateMiddleSelect.jsp",
					data : {lcate:value},
					dataType : "xml",
					type : "GET",
					cache:false,
					success : setCombo, 
					error : function(err) {
						alert("나오면 안되는데 왜 나왔니?==>"+err);
					}
				});
			}
			function setCombo(data) {
				//	할일
				//		1.	콤보의 기존에 내용은 지운다.
				//			1)	콤보상자를 알아내고
				
				/*
				var	combo = document.getElementById("mcate");
				var	len = combo.length;
				//			2)	그 갯수만큼 반복하면서 지운다.
				for(i = 0; i < len; i++) {
					combo.remove(0);
				) */
				
				$("#mcate").empty();
				
				//	강제로 == 선택하세요를 붙인다.
		/*		
				var	option = document.createElement("option");
				option.text = "== 선택하세요 ==";
				option.value = "0";
				combo.options.add(option);
				*/
				 
				 var option = "<option value=\"0\"> == 선택하세요 == </option>";
				 
				 $("#mcate").append(option);
			
				// xml 방식 
				
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
				//		2.	콤보에 새로운 내용으로 채운다.
				//		응답받은 데이터를 이용해서 내용을 만들어서 채운다.
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
	//	할일
	//		먼저 데이터베이스를 뒤져서 대 카테고리를 알아낸다.
	WebDB			db = null;
	Connection	con = null;
	Statement		stmt = null;
	ResultSet		rs = null;
	ArrayList		list = new ArrayList();
	try {
		db = new WebDB();
		con = db.getCON();
		stmt = db.getSTMT(con);
		//	대 카테고리만 꺼내는 질의를 던진다.
		String	sql = "SELECT * FROM GoodsCate WHERE gc_Code LIKE '____'";
		rs = stmt.executeQuery(sql);
		//	여기서 나온 대 카테고리를 ArrayList에 묶어서 사용하자.
		while(rs.next()) {
			//	각각의 카테고리를 코드값, 이름 이라는 두개의 데이터를 가지고 있으므로....
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
						<option value="0">==선택하세요==</option>
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
						<option value="0">==선택하세요==</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="lname" id="lname">
					<input type="button" value="대카테고리 등록" onClick="JavaScript:LREG()">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="mname" id="mname">
					<input type="button" value="중카테고리 등록" onClick="JavaScript:MREG()">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>





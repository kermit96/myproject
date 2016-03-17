<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
$(document).ready(function() {	
});

function send()
{
    $.ajax({
       url:"JsonMapListReq.jsp",
       dataType:"json",
       type:"get",
       success:ShowData,       
       error:function(xhr) {
    	     alert( xhr.status + " " + xhr.statusText);
       }       
    });     
}

function ShowData(data) {
	//  al    ert("aa"+data);
	 var friends = data.kimfriend;
	 
	 
	 var h= "<table border='1' align='center' width='70%'>";
	 /*
	  $.each(friends,function(index) {
		    var imsi = friends[index].Friend;
		    h+="<tr>";
		    h+="<td>";
		    h+=imsi.name;
		    h+="</td>";
		    h+="<td>";
		    h+=imsi.phone;
		    h+="</td>";
		    h+="<td>";
		    h+=imsi.addr;
		    h+="</td>";
		    h+="<td>";
		    h+=imsi.age;
		    h+="</td>";
		    h+="</tr>"
	  });
	 */
	 

	 
	 try {
	 for(tmp in  friends) 
	 {
		 
		    var imsi = friends[tmp].Friend;
		    
		    h+="<tr>";
		    h+="<td>";
		    h+=imsi.name;
		    h+="</td>";
		    h+="<td>";
		    h+=imsi.phone;
		    h+="</td>";
		    h+="<td>";
		    h+=imsi.addr;
		    h+="</td>";
		    h+="<td>";
		    h+=imsi.age;
		    h+="</td>";
		    h+="</tr>"		 	 		 
	 }
	 
	 } catch(ex) {
		 
		 alert(ex);
	 }
	  h+="</table>"
	  $("#div1").html(h);
}
</script>
</head>
<body>
<div id="div1">

</div>

<input type="button"  value="눌러"  onClick="send()">
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script>

$(document).ready(function() {

$(function() {
    $( "#datepicker1" ).datepicker({
   
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달',
        dateFormat: "yy-mm-dd"
    });
});

$(function() {
	
    $( "#datepicker2" ).datepicker({
        changeMonth: true, 
        changeYear: true,
        showButtonPanel: true, 
        nextText: '다음 달',
        prevText: '이전 달',   
        currentText: '오늘 날짜', 
        closeText: '닫기', 
        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
       dateFormat: "yy/mm/dd"
    });
});

$("#datepicker2").val("2016/10/22");   
});
</script>

</head>
<body>
<!--  <input type="text" id="txtDate" onclick="fnPopUpCalendar(txtDate,txtDate,'dd/mm/yyyy')"/> -->
<input type="text" id="datepicker1">

<input type="text" id="datepicker2">
</body>

<script>



</script>
</html>
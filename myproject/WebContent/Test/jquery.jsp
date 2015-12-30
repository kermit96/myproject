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
	
	$("[name='test22']").css("cursor","pointer");
//  	     $("[name]").click(hidea);
//	     $("[name='aa'']").dblclick(hidea);

         $("[name='aa']").mouseenter(mouseenter);
         try {
         $("[name='aa']").mouseleave(mouseleave);
         $("[name='aa']").mousedown(mousedown);
         $("[name='aa']").mouseup(mouseup);
         $("[name='aa']").hover(mousehover);
         $("[name='aa']").focus(mousefocus);
         $("[name='aa']").blur(mouseblur);
      } catch(ex) {
    	  
    	  alert(ex);
      }
   	     
});

function mouseenter()
{
	$("#divid").html("mouseenter");
}

function mouseleave()
{
	$("#divid").html("mouseleaver");
}

function mousedown()
{
	$("#divid").html("mousedown");
}

function mousefocus()
{
	$("#divid").html("mousefocus");
}


function mouseup()
{
	$("#divid").html("mouseup");
}

function mousehover()
{
	$("#divid").html("mousehover");
}



function mouseblur()
{
	$("#divid").html("mouseblur");
}



function hidea()
{
	$(this).hide();
}

</script>
</head>
<body>
<input type="text"   name="aa" value="aaa" >
<input type="button"   name="aa2" value="aaa222">
<p name="test22">  test  </p> 

<div id="divid" ></div>




</body>
</html>
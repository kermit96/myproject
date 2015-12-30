<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script src="http://cdn.ckeditor.com/4.5.6/standard-all/ckeditor.js"></script>
</head>
<body>
 
	<textarea name="editor1" id="editor1" rows="8" cols="80" >&lt;p&gt;This is some &lt;strong&gt;sample text&lt;/strong&gt;. You are using &lt;a href="http://ckeditor.com/"&gt;CKEditor&lt;/a&gt;.&lt;/p&gt;
	</textarea>

	<script>
	  try {
		  
		var editor1 = document.getElementById("editor1");
		  
		CKEDITOR.replace( 'editor1', {
			extraPlugins: 'image2',
			height: 350,
			enterMode:'2',
			shiftEnterMode:'2',
			 language : 'korea'			
		} );
		
	  } catch (ex) {
		  alert(ex);
		  
	  }
	</script>

</body>
<script>


	</script>
</html>
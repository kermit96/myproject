<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//code.jquery.com/jquery.min.js"> </script>
<script>
$(document).ready(function() {	
	$("#wBtn").click(wBtnClick);
	 
});

function wBtnClick()
{
     // location.href = "";
    $(location).attr("href","../FileBoard/FileBoardUploadForm.fbd");
    
      // 참고 attr() 는 속성을 변경하는 기능을 가진 함수이다.
      // 형식> 요소.attr("바꿀 속성","값");
      // 예> $(img).attr("src","Girls.dif");
}

</script>
</head>
<body>
<%--  검섹 폼  --%>
<%--  검섹 폼  --%>
<%--  검섹 폼  --%>

    <table border="1" align="center"  width="80%" >
      <tr>
          <td align="center">
             <input type="button"  value="글쓰기"   id="wBtn" >
          </td>
      </tr>
    
    </table>


</body>
</html>
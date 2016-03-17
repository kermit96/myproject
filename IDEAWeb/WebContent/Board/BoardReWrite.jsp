<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	모델에서 알려준 데이터를 알아낸다.
	int		oriNO = (Integer) request.getAttribute("ORINO");
	//	보여줄것은 없고
	//	다시 상세보기로 보내면 된다.
	response.sendRedirect("../Board/BoardDetail.bbs?oriNO=" + oriNO);
	//	뷰에서 사용할 데이터가 있으면 그것은 MVC의 원리에 따라 모델에서 전달해야 한다.
	
	//	참고		GET 방식에서 파라메터를 보낼때 ?다음에는 절대로 공백이 있어서는 안된다.
	//	예>		?oriNO = 10				이렇게 쓰면 안된다.
	//				?oriNO=10					이렇게 써야 한다.
	//				?no=10 & name=hong	이렇게 쓰면 안된다.
	//				?no=10&name=hong		이렇게 써야 한다.
%>
	</body>
</html>








<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"> </script>
</head>
<body>
        ㅇㄹㄹㅇ
<%--	설문조사 문항보기 페이지 --%>
		<form method="POST" action="SurveyInsert.jsp">
			<table width="70%" border="1" align="center">
				<tr>
					<td colspan="2" align="center">
						세상에서 가장 이쁜 사람으로 크리스마스를 같이 보내고 싶은 사람은 누구입니까?
					</td>
				</tr>
				<tr>
					<td>1. <input type="radio" name="dap" value="s_dap1" checked></td>
					<td>태연</td>
				</tr>		
				<tr>
					<td>2. <input type="radio" name="dap" value="s_dap2"></td>
					<td>한지민</td>
				</tr>		
				<tr>
					<td>3. <input type="radio" name="dap" value="s_dap3"></td>
					<td>하연수</td>
				</tr>		
				<tr>
					<td>4. <input type="radio" name="dap" value="s_dap4"></td>
					<td>김사랑</td>
				</tr>		
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="설문 참여하기">
<%--
	우리가 만든 모든 페이지는 하이퍼 링크 방식으로 사용자가 접근할 수 있도록 준비를 해주어야 한다.
	주소를 치고 들어가야 하는 문제는 없어야 한다.
 --%>						
 						<a href="SurveyResult.jsp">결과보기</a>
					</td>
				</tr>		
			</table>
		</form>
</body>
</html>
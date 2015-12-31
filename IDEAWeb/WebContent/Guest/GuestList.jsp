<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="dao.*, java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	//	웹은 우리가 제작할 때는 하이퍼 링크를 이용해서 접근하도록 제작한다.
	//	하지만 검색 결과등을 이용할 경우에는 직접 주소를 치고 들어오는 경우도 존재한다.
	//	이 경우에는 사용자가의 상태가 원하지 않는 상태일 수도 있다.
	//			예>		로그인을 해야만 방명록을 이용할 수 있는데.......
	//	결론
	//		이 페이지를 사용할 수 있는 권한이 있는지를 매번 점검을 해주어야 한다.
	
	String	id = (String)session.getAttribute("ID");
	if(id == null || id.length() == 0) {
		response.sendRedirect("../Member/LoginForm.jsp");
	}
	
%>
<%--
	아시다시피 방명록을 먼저 현재까지 방명록 내용을 보여주고
	마지막 부분에 방명록을 쓸수 있는 폼을 보여주는 형식을 취한다.
 --%>
<%--
	방명록 내용보기
 --%>
<%
	//	방명록 내용을 보려면 방명록을 셀렉트한 결과를 얻어와야 한다.
	GuestDao		dao = new GuestDao();
	ArrayList		list = dao.getGuest();
	dao.close();
%>
<%
	for(int i = 0; i < list.size(); i++) {
		HashMap	map = (HashMap) list.get(i);
%>
		<table width="70%" border="1" align="center">
			<tr>
				<td>글번호</td>
				<td><%= map.get("NO") %></td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td><%= map.get("WRITER") %></td>
			</tr>
			<tr>
				<td>날짜</td>
				<td><%= map.get("DATE") %></td>
			</tr>
			<tr>
				<td>본문</td>
				<td><%= map.get("CONTENT") %></td>
			</tr>
		</table>		
<%
	}
%>	
<%--
	방명록 글쓰기 폼
 --%>
		<form method="POST" action="GuestInsert.jsp">
			<table border="1" width="70%" align="center">
				<tr>
					<td>글쓴이</td>
					<td>
						<input type="text" disabled value="<%= session.getAttribute("ID") %>">
					</td>
				</tr>
				<tr>
					<td>본문</td>
					<td>
						<textarea name="body" cols="50" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="방명록 쓰기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>











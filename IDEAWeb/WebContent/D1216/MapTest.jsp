<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
<%
	HashMap	map = new HashMap();
	map.put("홍길동", "보훈처? 누구를 위한 애국심팔이 보훈이며 무슨 저의로 저지르는 시정 흔들기의 저급한 깽판인가? 애국선열과 태극기가 통곡한다. 이 못난 넘들아!");
	map.put("박아지", "독립운동후손분들이나 제대로 챙겨라.. 친일파후손들 독립운동가의 후손으로 포장하지말고..");
	map.put("장독대", "보훈처 놈들, 보훈 사업이나 잘 할이지, 주제넘게 자신들의 업무와 무관한 웬 태극기 사업으로 정치질이냐? 애국심은 자연스럽게 우러나는 거지, 시민들이 이용하는 광장에 대형 태극기 게양대를 왜 만드나. 미친 것들");
	map.put("이기자", "보훈처는 국가유공자에 대한 예우부터 갖춰라. 쓸데 없는데 시간 낭비, 예산 낭비 하지 말고. 복지 복지 복지 외치면서 정작 나라 위해 희생한 분들에게는 나몰라라 하는 보훈처 양심은 있는가?");
	map.put("정말로", "도대체 우리나라는 말 같지 않은 말을 하는 자들이 왜 이리 많은지...");

	String	key = request.getParameter("name");
	String	data = (String) map.get(key);
%>
		<dl>
			<dt><%= key %>	</dt>
			<dd><%= data %></dd>
		</dl>
	</body>
</html>

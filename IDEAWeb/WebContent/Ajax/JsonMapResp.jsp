<%@ page language="java" contentType="text/plane; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%--	
		이 문서는 아작스로 요청이 왔을때 응답할 문서이다.
		문제은 이 문서는 HTML로 응답하는 것이 아니고 순수한 데이터만
		JSON 형태로 응답할 예정이다.
		==>		contentType을 바꿔야 한다.
		JSON은 일반 텍스트 파일 형태이다.
 --%>
{"이름":"홍길동", "나이":"24", "성별":"남성", "신장":"175.5"}
<%--
	JSON방식은 모든 데이터가 문자로만 처리되어야 한다.
	즉, 모든 데이터는 "" 안에 포함되어야 한다.
--%>
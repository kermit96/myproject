<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			function Show(no) {
				$("#" + no).toggle();
			}
			
			function write() {
				
				location.href = "NoticeForm.do";
			}
			
	
			
			
			function delete2(){

				  if( $(":checkbox[name='namecheck']:checked").length==0 ){

				    alert("삭제할 항목을 하나이상 체크해주세요.");

				    return;

				  }

				  
				  var get = get_chked_values();
				  
				  alert(get);
				}

				function get_chked_values(){

				  var chked_val = "";

				  $(":checkbox[name='namecheck']:checked").each(function(pi,po){

				    chked_val += ","+po.value;

				  });

				  if(chked_val!="")chked_val = chked_val.substring(1);

				  return chked_val;

				}
			
			
		</script>
	</head>
	<body>
		<table width="80%" border="1" align="center">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>&nbsp;</th>	<%--	나중에 펼침 기능 단추를 만들기 위해서 --%>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.rno}</td>
					<td>${temp.strkind} ${temp.title}</td>
					<td>${temp.writer}</td>
					<td>${temp.datetime}</td>
					<td>
					<%--	이 단추도 여러개 만들어질 예정이고
							단추를 누르면 원하는 부분이 펼쳐져야 한다.
							단추를 누르때 원하는 것을 알려주어야 한다.
							
					 --%>
						<input type="button" value="보기" onClick="JavaScript:Show(${temp.no})">
						<input type="checkbox"   name="namecheck" value="${temp.no}">
					</td>
				</tr>
				<%--	본문까지 출력을 해 놓고 감춰놓자. 
						이 부분은 단추를 누르면 나오게 해야 한다.
						근데...
						이 부분은 여러개 발생한다.
						고로 이 부분을 어떻게 해서든지 구분할 수 있게 만들어야 한다.
						나는
						이 부분의 id를 게시물의 글번호로 만들어 줌으로 구분하도록 할 예정
				--%>
				<tr id="${temp.no}" style="display:none">
					<td colspan="5">
					<pre>${temp.body}</pre>
					</td>
				</tr>
			</c:forEach>
		</table>
<%--  숙제	페이지 이동 기능을 만들자 --%>
		<table width="80%" border="1" align="center">
			<tr>
				<td align="center">
				<c:if test="${PINFO.startPage eq 1}">
					[이전]
				</c:if>
				<c:if test="${PINFO.startPage ne 1}">
					<a href="../Notice/NoticeList.sun?nowPage=${PINFO.startPage - 1}">[이전]</a>
				</c:if>
				<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
					<a href="../Notice/NoticeList.sun?nowPage=${temp}">[${temp}]</a>
				</c:forEach>
				<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다음]
				</c:if>				
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="../Notice/NoticeList.sun?nowPage=${PINFO.endPage + 1}">[다음]</a>
				</c:if>				
				</td>
			</tr>
		</table>
		
<table width="80%" border="1" align="center"> 
		<tr>
		  <td >  <a href="NoticeForm.do" > 공지사항 쓰기</a> </td>
		
		<tr>
		  <td >  <button onclick="delete2()"> 삭제 </button> </td>
		  
		  
		</tr>
		</table>
		
	</body>
</html>


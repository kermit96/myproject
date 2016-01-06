<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery.min.js">
	
</script>
<script>
	function check() {
		try {
			check2();

		} catch (ex) {

			alert(ex);
		}

	}

	function check2() {

		var min1 = $("#min1").val();

		var min2 = $("#min2").val();

		if (min1.length != 6) {
			alert(" 첫번째 잘못된 입력입니다.");
			return;
		}

		if (min2.length != 7) {
			alert("두번째  잘못된 입력입니다.");
			return;
		}

		min1 = min1 + min2;

		if (isNaN(min1)) {
			alert("숫자만 입력할수 있습니다.")
			return;
		}
		var birthYear = (min2.charAt(6) <= "2") ? "19" : "20";
		birthYear += min1.substr(0, 2);
		var birthMonth = "" + (parseInt(min1.substr(2, 2)) - 1);
		var birthDate = min1.substr(4, 2);
		var birth = new Date(birthYear, birthMonth, birthDate);

		if (birth.getYear() % 100 != min1.substr(0, 2)
				|| birth.getMonth() != birthMonth
				|| birth.getDate() != birthDate) {
			alert("잘못된 생년 월일이다.==>" + birth.getMonth() + "/" + birth.getDate());
			return false;
		}

		var mul = [ 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 ];

		var sum = 0;

		for (var i = 0; i < min1.length - 1; i++) {
			sum += parseInt(min1.charAt(i)) * mul[i];
		}

		var jumin = (11 - (sum % 11)) % 10;

		if (jumin == parseInt(min1[12])) {
			alert("주민 등록 번호 맞음 ")
		} else {
			alert("주민 등록 번호 틀림 ")
		}

	}
</script>
</head>
<body>
	<form>

		주민 번호 <input type="number" id="min1">-<input type="number"
			id="min2" checked="checked"> <input type="button"
			onclick="check()" value="체크 번호">
	</form>




</body>
</html>
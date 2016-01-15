package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDelete implements BoardMain {
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("나는 삭제 요청 함수이다.");
		return "";
	}
}

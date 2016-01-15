package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardModify implements BoardMain {
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("나는 수정 요청 함수이다.");
		return "";
	}
}

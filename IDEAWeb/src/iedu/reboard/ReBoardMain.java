package iedu.reboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 	이 인터페이스는 모든 모델의 상위 클래스 역활을 인터페이스이다.
 */

public interface ReBoardMain {
	//	계층 추적을 하기 위해서 함수를 가지고 있어야 한다.
	public String execute(HttpServletRequest req, HttpServletResponse resp);
	//	약속
	//	모든 모델은 이 인터페이스를 상속 받아서 제작해야 한다.
	//	그리고 그 안에 execute()를 오버라이드 해 놓아야 한다.
}


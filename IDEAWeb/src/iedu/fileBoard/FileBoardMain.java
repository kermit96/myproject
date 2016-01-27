package iedu.fileBoard;

import 	javax.servlet.http.*;

/*
 * 	이 클래스는 모든 모델 클래스의 상위 클래스 역활을 할 예정이다.
 */
public abstract class FileBoardMain {
	//	★★★★★
	//		다형성 구현을 하면 상위 클래스 부터 계층을 추적을 하면서 함수를 실행한다.
	//		그리고 상위 클래스가 그 함수가 존재하지 않으면 에러가 발생한다.
	//	이 클래스에 계층 추적을 위한 함수를 제작해 놓자.
	public abstract String service(HttpServletRequest req, HttpServletResponse resp);
}

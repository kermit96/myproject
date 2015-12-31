package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 	이 인터페이스는 실제 작업을 수행할 모델 클래스를 위해서 다형성을 구현할 상위 클래스
 * 	역활을 할 예정이다.
 * 
 * 	모델 클래스란	요청에 의해 필요한 데이터를 생산하는 클래스를 말한다.
 */
public interface BoardMain {
	public String action(HttpServletRequest req, HttpServletResponse resp);
}

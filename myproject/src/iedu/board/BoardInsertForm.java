package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모델이 되기 위해서는 메민 콘틀러가 다형서을 이용해서 처리하도록 약속했으므로
// 다형성 구현을 위한 인터페이스를 상속받아서 만든다 .
public class BoardInsertForm  implements BoarderMain {
@Override
public String action(HttpServletRequest req, HttpServletResponse resp) {
	// TODO Auto-generated method stub
	
	return "/Boarder/BoardInsertForm.jsp";
}
} 

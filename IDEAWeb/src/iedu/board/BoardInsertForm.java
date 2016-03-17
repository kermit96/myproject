package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//	모델이 되기 위해서는 메인 컨트롤러가 다형성을 이용해서 처리하도록 약속했으므로
//	다형성 구현을 위한 메인 인터페이스를 상속받아서 만든다.
public class BoardInsertForm implements BoardMain {
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		글쓰기 폼을 구성함에 있어서 필요한 데이터를 생산해준다.
		//		하지만 폼을 구성함에 있어서 필요한 데이터는 없다.
		//		고로 아직은 할 일이 없다.
		
		return "/Board/BoardInsertForm.jsp";
	}
}

package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.dao.BoardSql;
import iedu.dao.DBTYPE;

public class BorderWriteSave implements BoarderMain {
	@Override
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
//		System.out.println("나는 쓰기 요청함수이다");
		// 할일 
		// 넘어온 파라메테를 받아낸다. 
		 
		// 결과물은 없으므로  뷰를 선택한다.
		
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		String id = (String)req.getSession().getAttribute("id");
		
		
		BoardSql sql = new BoardSql( DBTYPE.MYSQL_TYPE );
		  
		sql.insertBoard(id, title, body);
		 
							
		return "/Boarder/BoarderOk.jsp";
	}
}

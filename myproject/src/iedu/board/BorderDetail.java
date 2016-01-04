package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.dao.BoardDao;
import iedu.sql.BoardSql;

public class BorderDetail  implements BoarderMain {
@Override
public String action(HttpServletRequest req, HttpServletResponse resp) {
	// TODO Auto-generated method stub
    // System.out.println("나는 상세보기 요청함수이다");
	
	String  str = req.getParameter("oriNo");
	
	int oriNo= Integer.parseInt(str);
	
   	BoardSql   sql = new BoardSql();
	
   	BoardDao board = sql.GetDetailBoard(oriNo);
	board.content = board.content.replaceAll("\r\n", "<br>"); 
   	req.setAttribute("SETDATA", board);
	return "/boarder/BoardDetail.jsp";
	      
}

}

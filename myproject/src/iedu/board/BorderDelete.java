package iedu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.dao.BoardDao;
import iedu.dao.BoardSql;

public class BorderDelete  implements BoarderMain {
	 @Override
	public String  action(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
        // System.out.println("나는 삭제 요청함수이다");
		 
			String  str = req.getParameter("oriNo");
			
			int oriNo= Integer.parseInt(str);
			
		   	BoardSql   sql = new BoardSql();
			
		//   	BoardDao board = sql.(oriNo);
		 //  	req.setAttribute("SETDATA", board);

		   
  		 return "/Boarder/BorderDelete.jsp";
	}
}  

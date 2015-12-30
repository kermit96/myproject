package iedu.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.dao.BoardDao;
import iedu.dao.BoardSql;
import iedu.dao.DBTYPE;

public class BorderList implements BoarderMain {
	@Override
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	    // 생산된 데이터를 뷰에서 사용할 수 있도록 저장한 수
		
		// 이 데이터를 이용하는 뷰를 선택하도록 한다. 
	
		// 이 데이터를 이용하는 뷰를 선택하도록한다.
		// 참고 뷰가 결정되는 것이 아니라 선택하도록 하는 이유는 ?
		
		
		// 뷰가 달라지면 controller 에게 뷰의 이름을 컨트롤러에게 알려주기로 약속했다. 
	
		 BoardSql bbs = new  BoardSql(DBTYPE.MYSQL_TYPE );
		 
		 int nowPage =1;
		 
		 try {
			 nowPage = Integer.parseInt(req.getParameter("nowPage"));
			 
		 } catch (Exception ex) {
			 
			 
		 }
		 
		 ArrayList<BoardDao> array =   bbs.ListBoard( nowPage );
		
		 req.setAttribute("Data", array);
		 
		 return "/Boarder/BoarderList.jsp";
		 
	         
	}
}

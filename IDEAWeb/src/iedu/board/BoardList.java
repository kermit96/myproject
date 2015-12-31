package iedu.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iedu.util.PageInfo;
import jdbc.WebDB;
/*
 * 	이 클래스는 목록 보기 요청이 들어오면 목록을 만들 데이터를 생산할 모델 클래스이다.
 */
public class BoardList implements BoardMain {
	//	이 함수가 컨트롤러에 의해 실행될 메인 함수의 역활을 할 것이다.
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		//	할일
		//		넘어온 파라메터를 받는다.
		String		strPage = req.getParameter("nowPage");
		int			nowPage = 0;
		//	선택요소인 경우에는 넘어오지 않았을 경우를 반드시 대비해 주어야 한다.
		if(strPage == null || strPage.length() == 0) {
			//	넘어오지 않으면 1페이지부터 보여주기로 한다.
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		//	이 부분에서 원하는 데이터를 생산해 낼것이다.
		//	생산이 다 끝났으면.... 
		//	생산된 데이터를 뷰에서 사용할 수 있도록 저장한 후
		
		//	이 데이터를 이용하는 뷰를 선택하도록 한다.(뷰가 결정되지 않았다.)
		//	참고	뷰가 결정되는 것이 아니라 선택하도록 하는 이유는?
		//			모델이 처리 결과에 따라 사용할 뷰가 달라질 수도 있기 때문이다.
		//		예>	정상적인 경우
		//				비정상적인 경우			사용할 뷰가 달라질 수 있다.
		
		//	그래서 선택하는 원리는 사용할 뷰의 이름을 컨트롤러에게 알려주기로 약속했다.
		
		//	목록 보기에 필요한 데이터를 생산한다.
		//		목록 보기에 필요한 데이터는 데이터베이스에 있는 내용들이다.
		WebDB		db = null;
		Connection	con = null;
		Statement	stmt = null;
		ResultSet		rs = null;
		ArrayList		list = new ArrayList();
		//	페이지 정보를 기억할 유틸리티 클래스를 준비한다.
		PageInfo		pInfo = null;
		//	이 페이지 정보를 만들기 위해서는 현재 페이지와 총 데이터개수를 알려주어야 한다.
		try {
			db = new WebDB();
			con = db.getCON();
			stmt = db.getSTMT(con);
			//	총 데이터 개수를 구해보자.
			String		sql = "SELECT COUNT(*) as CNT FROM ReBoard WHERE rb_isShow='Y'";
			rs = stmt.executeQuery(sql);
			rs.next();
			int	totalCount = rs.getInt("CNT");
			db.close(rs);
			//	페이지 정보를 만들자
			pInfo = new PageInfo(nowPage, totalCount, 3, 5);
			pInfo.calcInfo2();
			
			
			sql = "SELECT * FROM ReBoard WHERE rb_isShow='Y' ORDER BY rb_NO DESC";
			rs = stmt.executeQuery(sql);
			//	이제 이 결과물을 뷰에게 알려주어야 한다.
			//		결과물은	한줄에 여러개의 데이터가 존재하고		Map으로 묶자
			//		이 줄이 여러줄이 존재한다.							ArrayList로 묶자

			
			//	이 부분은 질의 명령 결과를 다 꺼내는 부분이다.
			//	우리는 3개만 꺼내면 된다.
			//	물론 서브 질의를 이용하면 데이터베이스에서 꺼낼때 3개만 꺼낼 수 있다.
			//	하지만 오늘은 이미 질의를 사용했으므로 이부분을 수정해서 처리할 예정이다.
			
			//	원리
			//		이젠 페이지에서 보여줄 내용은 스킵시킨다.
			//		예>		지금	2페이지를 보여줄 차례이면 이전에 있던 3개는 스킵시킨다.
			for(int i = 0; i < pInfo.pageList * (nowPage - 1); i++) {
				rs.next();
			}
			//		지금 페이지에 보여줄 내용만 꺼낸다.
			for(int i = 0; i < pInfo.pageList && rs.next(); i++) {
				HashMap	map = new HashMap();
				map.put("NO", rs.getInt("rb_NO"));
				map.put("TITLE", rs.getString("rb_Title"));
				map.put("ID", rs.getString("rb_Writer"));
				map.put("DATE", rs.getDate("rb_Date"));
				map.put("HIT", rs.getInt("rb_Hit"));
				
				list.add(map);
			}
		}
		catch(Exception e) {
			System.out.println("목록 보기 에러 = " + e);
		}
		finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		//	이제 만들어진 데이터를 뷰에게 알려주어야 한다.
		//	뷰에게 알려주는 방법
		//		1.		application.setAttribute()
		//				이 데이터는 모든 뷰에서 다 사용할 수 있는 데이터가 된다.
		//		2.		session.setAttribute()
		//				이 데이터는 같은 세션을 가진 뷰에서 사용할수 있는 데이터가 된다.
		//		★★★
		//		3.		req.setAttribute()
		//				이 모델이 선택한 뷰에서만 사용할 수 있는 데이터가 된다.
		
		//	참고	setAttribute는 Map 형식으로 알려주는 방식으로 데이터를 키값과 함께 등록해야 한다.
		req.setAttribute("DATA", list);
		//	뷰에서는 페이지 정보도 이용해야 하므로 같이 보내준다.
		req.setAttribute("PINFO", pInfo);
		//	이처럼 모델은 생산된 데이터를 뷰가 필요하면 몇개든지 보낼 수 있다.
		return "/Board/BoardList.jsp";
	}
}



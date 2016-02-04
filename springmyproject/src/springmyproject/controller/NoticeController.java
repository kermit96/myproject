package springmyproject.controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springmyproject.dao.NoticeDao;
import springmyproject.data.NoticeData;
import springmyproject.sql.NoticeSql;
import springmyproject.util.PageInfo;;

@Controller
public class NoticeController {
	@RequestMapping("/Notice/NoticeForm") 
	public ModelAndView NoticeForm(HttpServletRequest request,HttpServletResponse response, HttpSession session)
	{
		
		System.out.println(session.getId());
	        	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Notice/NoticeForm");  
		return mv;
	}
	
	@RequestMapping("/Notice/NoticeList")
	public  ModelAndView  noticeList(HttpServletRequest req) 
	{		
//		할일	
			//	파라메터 받는다
			String	strPage = req.getParameter("nowPage");
			int		nowPage = 0;
			if(strPage == null || strPage.length() == 0) {
				nowPage = 1;
			}
			else {
				nowPage = Integer.parseInt(strPage);
			}

			//	데이터베이스에서 목록 꺼낸다.
			NoticeDao	dao = new NoticeDao();
			//	1.	총 데이터 개수
			int		total = dao.getTotal();
			//	2.	페이지 기능을 만들자.
			PageInfo	 pInfo = new PageInfo(nowPage, total, 5, 5);
			pInfo.calcInfo();
			//	3.	실제 목록에 출력할 데이터 검색
			//		문제
			//			실제 목록을 구할때는 원하는 위치를 알려주어야 한다.
			//	예>		1페이지		1 ~ 5
			//			2페이지		6 ~ 10	
			//	위치를 찾는다.(? 두개를 채워줄 시작위치와 종료위치를 찾는다.)
			int		start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
			int		end = start + pInfo.pageList - 1;
			//	마지막 페이지는 그 개수가 부족할 수 있다.
			if(end > pInfo.totalCount) {
				end = pInfo.totalCount;
			}
			ArrayList	list = dao.getList(start, end);
			dao.close();
			
			ModelAndView		mv = new ModelAndView();
			//	뷰에게 알려준다.
			mv.addObject("PINFO", pInfo);
			mv.addObject("LIST", list);
			//	뷰를 선택한다.
			mv.setViewName("Notice/NoticeList");
			return mv;
	}
	
// 	public void noticeWrite( HttpServletRequest req,@PathVariable("title") String title) {
	
// 	public void noticeWrite( HttpServletRequest req,@RequestParam Map<String, String> map) {
		
//	public void noticeWrite( HttpServletRequest req,@RequestParam Map<String, String> map) {
	/*
	 * 글쓰기 요청 처리 모델 함수
	 */
	@RequestMapping("/Notice/NoticeWrite")
	public ModelAndView noticeWrite(NoticeData data) {
		//	파라메터 받는 방법
		//	1.	req를 이용해서 받는다.
		//		String	title = req.getParameter("title");
		//	2.	매개변수를 이용해서 받는 방법
		//		1.	하나씩 받는 방법
		//			형식1>		(@PathValiable("키값") String title)
		//			형식2>		(@RequestParam("키값") String title)
		//		2.	Map으로 받는 방법(전체를 다 받는 방법)
		//			형식>		(@RequestParam Map<String, String> data)
		//	3.	데이터 빈 클래스를 이용해서 받는 방법
		//		우리가 배운 데이터 빈 클래슬 제작한 후
		//		그 데이터 빈 클래스를 매개변수에 등록하면 된다.
		
         NoticeDao  dao =new  NoticeDao();
         dao.noticeWrite(data);
		  dao.close();
		  
		//	데이터베이스에 기록하는 방법은 MVC 방식과 동일하다.
		//	즉,	일반 컨넥션, 컨넥션 Pool을 이용해서 컨넥션을 받은 후
		//	DAO를 이용해서 처리하는 방식
		
		//	뷰를 선택하는 방법
		//		1.	setViewName()
		//				일반적인 뷰를 선택하는 방법
		//		2.	setView()
		//				일반적인 뷰가 아닌 특수 뷰를 선택하는 방법
		//	특수뷰
		//		RedirectView		
		//		==>		스프링 모델 안에서 직접 다른 요청을 실행하는 뷰이다.
		//				우리가 아는 sendRedirect를 처리하기 위한 뷰이다.
		//	사용방법1>		new 를 시킬때 Redirect할 요청을 쓴다.
		//	RedirectView		view = new RedirectView("../Notice/NoticeForm.sun");
		
		//	사용방법2>		디폴트를 이용해서 new 시킨 후
		//						함수(setUrl)를 이용해서 처리한다.
		
		RedirectView		view = new RedirectView();
		view.setUrl("../Notice/NoticeList.do");
		view.addStaticAttribute("nowPage", 1);
		view.setExposeModelAttributes(false);
		//	참고	스프링에서는 Redirect 시킬때 파라메터를 줄 수 있다.
		//		방법1>		요청에 뒤에 ? 를 붙여서 파라메터를 준다.
		//			예>		../Notice/NoticeForm.sun?nowPage=1
		//		방법2>		함수(addStaticAttribute)를 이용해서 준다.
		//			예>		view.addStaticAttribute("nowPage", 1);
		
		//	참고	파라메터를 전송할 때 일반적으로 GET방식을 이용해서 처리한다.
		//			POST 방식으로 파라메터를 전송하고자 할 경우에는
		//			1.	반드시 방법2를 이용해서 파라메터를 기술한후
		//			2.	setExposeModelAttributes(false);	로 지정한다.

		//	위의 내용을 이용해서 RedirectView를 생성한 후
		//	ModelAndView에 setView()를 이용해서 만들어진 RedirectView를 등록한다
		
		ModelAndView mv = new ModelAndView();
		mv.setView(view);
		return mv;
	}
}

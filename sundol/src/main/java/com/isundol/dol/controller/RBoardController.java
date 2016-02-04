package com.isundol.dol.controller;

import 	java.util.ArrayList;
import java.util.HashMap;

import 	javax.servlet.http.HttpServletRequest;
import 	javax.servlet.http.HttpSession;

import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import 	org.springframework.web.servlet.ModelAndView;
import 	org.springframework.web.servlet.view.RedirectView;

import 	com.isundol.dol.dao.RBoardDAO;
import 	com.isundol.dol.data.RBoardData;
import 	com.isundol.dol.util.PageUtil;
import 	com.isundol.dol.util.SessionUtil;
import 	com.isundol.dol.util.StringUtil;

@Controller
public class RBoardController {
	//	사용할 DAO를 DI 기법으로 받아오자.
	//	참고로 하나의 컨트롤러가 여러개의 DAO를 사용해도 상관이 없다.
	@Autowired
	RBoardDAO	rDao;
	
	/*
	 * 목록보기 요청 함수
	 */
	@RequestMapping("/RBoard/BoardList")
	public ModelAndView boardList(HttpServletRequest req, HttpSession session) {
		ModelAndView 	mv = new ModelAndView();
		//	로그인한 사람만 목록을 보여주고 싶으면.....
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		
		//	할일
		//		넘어온 파라메터 받는다.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		//		페이지 나눔 기능을 만든다.
		//		페이지 나눔 기능을 만들기 위해서는 총 데이터 개수를 알아야 한다.
		int		total = rDao.getTotal(1);
		//		페이지 나눔 기능을 만든다.
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		
		//		목록보기 내용을 꺼낸다.
		ArrayList	list = rDao.getBoardList();
		//	이미 DAO에서 데이터를 ArrayList에 몽땅 담아서 준것이다.
		//	우리는 질의 명령을 단순화 하기 위해서 전체를 꺼내는 방식으로 했으므로....
		//	그 페이지에서 필요한 것만 꺼내서 뷰에게 주어야 한다.
		//			1페이지		0~4
		//			2페이지		5~9
		//			3페이지		10~14
		
		//	list에 있는 내용중에서 그 페이지에 필요한 내용이 어디서부터 어디까지 인지를 
		//	알아낸다.
		
		int		start = (pInfo.nowPage - 1) * pInfo.pageList;
		int		end = start + pInfo.pageList - 1;
		//	마지막 페이지인 경우를 처리한다.
		if(end >= list.size()) {
			end = list.size() - 1;
		}
		//	필요한 갯수만 꺼내서 다시 ArrayList에 담는다.
		ArrayList	result = new ArrayList();
		for(int i = start; i <= end; i++) {
			RBoardData	temp = (RBoardData)list.get(i);
			result.add(temp);
		}
		
		//		뷰를 선택한다.
		//	뷰에게 전달할 내용을 준다.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		mv.setViewName("RBoard/BoardList");
		return mv;
	}
	/*
	 * 	글쓰기 폼 요청 처리 함수
	 */
	@RequestMapping("/RBoard/BoardWriteForm")
	public ModelAndView boardWriteForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	로그인을 하지 않은 사람은 로그인을 하도록 한다.
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		//	로그인을 한사람에게는 그냥 폼을 보여주면 된다.
		//	특별히 모델에서 할 일은 없다.
		//	모델이 할 일이 없는데 그냥 jsp 문서를 부르면 안되나요?
		//	답은 된다.
		//	다만	프로그램 유지 보수를 위해서 모델을 사용하는 척 한다.
		//		컨트롤러를 거치는 jsp와 일반 jsp문서가 위치가 다르다.
		mv.setViewName("RBoard/BoardWriteForm");
		return mv;
	}
	
	/*
	 * 	글쓰기 요청
	 */
	@RequestMapping("/RBoard/BoardWrite")
	public ModelAndView	 boardWrite(HttpSession session, RBoardData data) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		0.	권한 검사한다.
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		//		1.	넘어온 데이터를 받는다.
		//			데이터빈 클래스를 이용해서 받는 방식으로 처리하고자 한다.
		//			매개변수에 데이터빈 클래스를 받도록 해주면 된다.
		
		//		2.	데이터베이스에 기록한다.
		//			3개의 ?를 채울 데이터는 이미 받아놓았다.
		//			1개가 없다.		글쓴이의 id가 없다.
		//			이것을 강제로 채워 놓는다.
		data.id = (String) session.getAttribute("ID"); 
		rDao.insertBoard(data);
		
		//		3.	뷰를 부른다.
		RedirectView	rv = new RedirectView("../RBoard/BoardList.dol");
		mv.setView(rv);
		return mv;
	}
	/*
	 * 조회수 증가 요청 처리 함수
	 */
	@RequestMapping("/RBoard/BoardHit")
	public ModelAndView boardHit(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		파라메터 받기
		String	strPage = req.getParameter("nowPage");
		//	필수 데이터가 없으면 역시 다른 페이지로 내보내 주어야 한다.
		if(StringUtil.isNull(strPage)) {
			RedirectView	rv = new RedirectView("../RBoard/BoardList.dol");
			mv.setView(rv);
			return mv;
		}
		int	nowPage = Integer.parseInt(strPage);
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
		
		//	조회수 증가
		//	증가하기 전에 조회수 증가가 가능한지 여부를 판단해야 한다.
		//	데이터베이스를 이용해서 처리하는 방법을 해보자.
		//	원리
		//		데이터베이스 안에
		//			아이디		봤던 글번호		기억하는 테이블을 만든다.
		//		해당 아이디를 이용해서 봤던 글번호를 검색해내고
		//		그 글번호 안에 현재 글번호가 포함되었는지를 확인하면 된다.
		String	id = (String) session.getAttribute("ID");
		//	1.	봤던 글 번호를 알아내자.
		HashMap	showMap = rDao.getShowno(id);
		boolean	isHit = false;		//	조회수 증가 여부를 판단할 변수	
		if(showMap == null || showMap.isEmpty()) {
			//	그런 아이디를 가진 사람은 아직 등록되지 않았다.
			//	고로 이 사람은 처음본 사람이다.
			isHit = true;
			//	이사람은 처음 글을 봤으므로 insert 시켜서 다음번 글을 볼때는
			//	검색이 되도록 해주어야 한다.
			HashMap 	temp = new HashMap();
			temp.put("ID", id);
			temp.put("NO", ":" + oriNo + ":");
			rDao.updateShowno(temp, 2);
		}
		else {
			//	뭔가 본 글이 존재한다.
			//	본 글중에서 현재 볼 글이 존재하는지 검사해야 한다.
			String	tempNo = ":" + oriNo + ":";
			String	dbNo = (String) showMap.get("SHOWNO");
			int index = dbNo.indexOf(tempNo);
			if(index == -1) {
				//	그런 글은 없어요
				isHit = true;
				//	이 사람이 다시 그 글을 볼때는 조회수 증가를 못하도록
				//	현재 글번호도 다시 넣어주어야 하겠다.
				HashMap	temp = new HashMap();
				temp.put("ID", id);
				temp.put("NO", dbNo + tempNo);
				rDao.updateShowno(temp, 1);
			}
			else {
				//	그런 글 있어요
				isHit = false;
			}
		}
		
		if(isHit == true) {
			//	조회수를 증가해도 되는 경우이다.
			rDao.updateHit(oriNo);
		}
		//	이제 뷰를 부른다.
		RedirectView	rv = new RedirectView("../RBoard/BoardView.dol");
		rv.addStaticAttribute("oriNo", oriNo);
		rv.addStaticAttribute("nowPage", nowPage);
		mv.setView(rv);
		return mv;
	}
		
	/*
	 * 	상세보기 요청 처리 함수
	 */
	@RequestMapping("/RBoard/BoardView")
	public ModelAndView boardView(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	할일	0	권한 검사하시고
		//	1.	넘어온 파라메터 받는다.
		String	strNo = req.getParameter("oriNo");
		int		oriNo = Integer.parseInt(strNo);
		String	strPage = req.getParameter("nowPage");
		int		nowPage = Integer.parseInt(strPage);
		
		//	2.	데이터베이스에서 상세보기를 요청하여 꺼낸다.
		RBoardData	map = rDao.selectView(oriNo);
		//	3.	댓글도 같이 꺼낸다.
		ArrayList		list = rDao.getReple(oriNo);
		
		//	4.	뷰에게 알려준다.
		mv.addObject("DATA", map);
		mv.addObject("NOWPAGE", nowPage);
		mv.addObject("RDATA", list);
		mv.setViewName("RBoard/BoardView");
		return mv;
	}
	
	/*
	 * 	댓글 등록 처리 함수
	 */
	@RequestMapping("/RBoard/BoardRepleWrite")
	public ModelAndView boardRepleWrite(RBoardData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	아이디를 알려주어야 insert를 할 수 있으므로
		String	id = (String) session.getAttribute("ID");
		data.id = id;
		//	이제 데이터베이스에게 기록하자.
		rDao.insertReple(data);

		//	상세보기로 Redirect 시킨다.
		RedirectView	rv = new RedirectView("../RBoard/BoardView.dol");
		rv.addStaticAttribute("nowPage", data.nowPage);
		rv.addStaticAttribute("oriNo", data.no);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	수정하기 폼 요청
	 */
	@RequestMapping("/RBoard/BoardModifyForm")
	public ModelAndView	 boardModifyForm(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		데이터 받는다.
		String	strPage = req.getParameter("nowPage");
		String	pw = req.getParameter("pw");
		String	strNo = req.getParameter("oriNo");
		int		nowPage = Integer.parseInt(strPage);
		int		oriNo = Integer.parseInt(strNo);

		//		원글의 비밀번호가 넘어온 비밀번호와 같은지 확인한다.
		HashMap	map = new HashMap();
		map.put("NO", oriNo);
		map.put("PW", pw);
		int	cnt = rDao.isUpdate(map);
		RBoardData	result = new RBoardData();
		if(cnt == 0) {
			//	수정 못하는 경우이다.
			//		Redirct를 다시 상세보기로 보낸다.
			RedirectView 	rv = new RedirectView("../RBoard/BoardView.dol");
			rv.addStaticAttribute("oriNo", oriNo);
			rv.addStaticAttribute("nowPage", nowPage);
			mv.setView(rv);
			return mv;
		}
		else {
			//	수정을 하는 경우이다.
			//	수정할 데이터를 꺼내서 뷰에게 알려준다.
			result = rDao.selectView(oriNo);
		}

		mv.addObject("DATA", result);
		mv.addObject("NOWPAGE", nowPage);
		mv.setViewName("RBoard/BoardModifyForm");
		return mv;
	}
	
	/*
	 * 	수정하기 요청 처리 함수
	 */
	@RequestMapping("/RBoard/BoardModify")
	public ModelAndView	boardModify(RBoardData data) {
		ModelAndView		mv = new ModelAndView();
		
		//	데이터베이스에게 수정을 요청한다.
		rDao.updateBoard(data);
		
		//	뷰는 상세보기를 다시 불러준다.
		RedirectView	rv = new RedirectView("../RBoard/BoardView.dol");
		rv.addStaticAttribute("oriNo", data.no);
		rv.addStaticAttribute("nowPage", data.nowPage);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	삭제하기 요청 처리 함수
	 */
	@RequestMapping("/RBoard/BoardDelete")
	public ModelAndView	boardDelete(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		넘어온 파라메터 받는다.
		String	strPage = req.getParameter("nowPage");
		String	pw = req.getParameter("pw");
		String	strNo = req.getParameter("oriNo");
		int		nowPage = Integer.parseInt(strPage);
		int		oriNo = Integer.parseInt(strNo);
		
		//		삭제 할 수 있는지 확인한다.
		HashMap	map = new HashMap();
		map.put("NO", oriNo);
		map.put("PW", pw);
		int	cnt = rDao.isUpdate(map);
		if(cnt == 0) {
			//	삭제하면 안되는 경우
			RedirectView 	rv = new RedirectView("../RBoard/BoardView.dol");
			rv.addStaticAttribute("oriNo", oriNo);
			rv.addStaticAttribute("nowPage", nowPage);
			mv.setView(rv);
			return mv;
		}
		else {
			//		삭제한다.
			rDao.deleteBoard(oriNo);
		}
		RedirectView	rv = new RedirectView("../RBoard/BoardList.dol");
		mv.setView(rv);
		//		뷰를 선택한다.
		return mv;
	}

	/*
	 * 	좋아요 추천 요청 함수
	 */
	@RequestMapping("/RBoard/BoardGood")
	public ModelAndView boardGood(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		1	넘어온 파라메터 받는다.
		String	strNo = req.getParameter("oriNo");
		int		oriNo = Integer.parseInt(strNo);
		//		2	추천을 계속할 수 없도록 조치한다.
		//			세션을 이용하던지, 데이터베이스를 이용하던지.........
		
		//		3.	데이터베이스에 추천을 하도록 한다.
		rDao.updateGood(oriNo);
		//		4.	현재 추천 내용을 알아온다.
		//		(아작스로 보냈으므로 결과를 보여주기 위해서는 현재까지 추천 상황을 알아야 한다)
		int	good = rDao.selectGood(oriNo);
		
		mv.addObject("GOOD", good);
		mv.setViewName("RBoard/BoardGood");
		return mv;
	}
}









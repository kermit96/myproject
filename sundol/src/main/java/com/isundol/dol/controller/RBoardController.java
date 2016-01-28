package com.isundol.dol.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import 	org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.isundol.dol.util.SessionUtil;
import com.isundol.dol.util.StringUtil;

@Controller
public class RBoardController {
	/*
	 * 목록보기 요청 함수
	 */
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
		
		//		목록보기 내용을 꺼낸다.
		
		//		뷰를 선택한다.
		
		mv.setViewName("RBoard/BoardList");
		return mv;
	}
}

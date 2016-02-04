package com.isundol.dol.controller;

import 	java.util.HashMap;

import 	javax.servlet.http.HttpServletRequest;
import 	javax.servlet.http.HttpSession;

import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import 	org.springframework.web.servlet.ModelAndView;

import 	com.isundol.dol.dao.MemberDAO;

@Controller
public class MemberController {
	//	이 컨트롤러에서 사용할 DAO는 물론 필요한 시점에서 new 시켜서 사용해도 된다.
	//	다만 스프링이 자랑하는 기술중에 DI 기법이 있으므로.....
	//	이 기법을 이용해서 처리하면 좋을것 같다.....
	@Autowired
	MemberDAO	mDao;
	
	/*
	 * 로그인 폼 요청 함수
	 */
	@RequestMapping("/Member/LoginForm")
	public ModelAndView loginForm() {
		ModelAndView		mv = new ModelAndView();
		//	할일		전혀없다.
		mv.setViewName("Member/LoginForm");
		return mv;
	}
	
	/*
	 * 	로그인 요청 함수
	 */
	@RequestMapping("/Member/LoginProc")
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		String	id 	= req.getParameter("id");
		String	pw = req.getParameter("pw");
		//	이제 데이터베이스에게 질의를 던져서 로그인을 해주도록 한다.
		//		1.	SQL를 만든다.
		//			com.isundol.dol.sql.MemberSQL.xml
		//		2.	DAO를 이용해서 데이터베이스 처리를 한다.
		//			com.isundol.dol.dao.MemberDAO.java
		
		//	이제 DAO를 이용해서 처리하고자 한다.
		//	DAO에게 필요한 정보를 HashMap으로 만들어서 제공하도록 약속했다.
		HashMap	map = new HashMap();
		map.put("PW", pw);
		map.put("ID", id);
		//	조심		키값은 SQL에서 지정한 #{키값}	과 동일해야 한다.
		HashMap	result = mDao.login(map);
		
		if(result == null || result.isEmpty()) {
			//	결과물이 존재하지 않니?		로그인 실패
		}
		else {
			//	결과물이 존재하니?			로그인 성공
			//	세션에 그 결과물의 정보를 입력해줌으로 나중에 세션을 이용해서
			//	이 사람을 정보를 얻을 수 있도록 해준다.
			session.setAttribute("ID", result.get("ID"));
			session.setAttribute("NAME", result.get("NAME"));
			session.setAttribute("NICK", result.get("NICK"));
			//	참고	반환값이 Map인 경우 Key값은
			//			자동적으로 AS에 사용한 내용이 키값으로 등록된다.
			//			만약 AS를 사용하지 않으면 필드이름이 키값으로 등록된다.
		}
		mv.setViewName("Member/LoginForm");
		return mv;
	}
	
	/*
	 * 로그아웃 요청 함수
	 */
	@RequestMapping("/Member/LoginOut")
	public ModelAndView loginOut(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		세션을 다 죽이고 로그인 폼으로 보낸다.
//		session.removeAttribute("ID");
//		session.removeAttribute("NAME");
//		session.removeAttribute("NICK");
		session.invalidate();
		//	참고
		//		원래는 이 부분에서 다른곳에서 사용했던 모든 세션을 같이 없애야 한다.
		mv.setViewName("Member/LoginForm");
		return mv;
	}
}



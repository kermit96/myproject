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
	//	�� ��Ʈ�ѷ����� ����� DAO�� ���� �ʿ��� �������� new ���Ѽ� ����ص� �ȴ�.
	//	�ٸ� �������� �ڶ��ϴ� ����߿� DI ����� �����Ƿ�.....
	//	�� ����� �̿��ؼ� ó���ϸ� ������ ����.....
	@Autowired
	MemberDAO	mDao;
	
	/*
	 * �α��� �� ��û �Լ�
	 */
	@RequestMapping("/Member/LoginForm")
	public ModelAndView loginForm() {
		ModelAndView		mv = new ModelAndView();
		//	����		��������.
		mv.setViewName("Member/LoginForm");
		return mv;
	}
	
	/*
	 * 	�α��� ��û �Լ�
	 */
	@RequestMapping("/Member/LoginProc")
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		String	id 	= req.getParameter("id");
		String	pw = req.getParameter("pw");
		//	���� �����ͺ��̽����� ���Ǹ� ������ �α����� ���ֵ��� �Ѵ�.
		//		1.	SQL�� �����.
		//			com.isundol.dol.sql.MemberSQL.xml
		//		2.	DAO�� �̿��ؼ� �����ͺ��̽� ó���� �Ѵ�.
		//			com.isundol.dol.dao.MemberDAO.java
		
		//	���� DAO�� �̿��ؼ� ó���ϰ��� �Ѵ�.
		//	DAO���� �ʿ��� ������ HashMap���� ���� �����ϵ��� ����ߴ�.
		HashMap	map = new HashMap();
		map.put("PW", pw);
		map.put("ID", id);
		//	����		Ű���� SQL���� ������ #{Ű��}	�� �����ؾ� �Ѵ�.
		HashMap	result = mDao.login(map);
		
		if(result == null || result.isEmpty()) {
			//	������� �������� �ʴ�?		�α��� ����
		}
		else {
			//	������� �����ϴ�?			�α��� ����
			//	���ǿ� �� ������� ������ �Է��������� ���߿� ������ �̿��ؼ�
			//	�� ����� ������ ���� �� �ֵ��� ���ش�.
			session.setAttribute("ID", result.get("ID"));
			session.setAttribute("NAME", result.get("NAME"));
			session.setAttribute("NICK", result.get("NICK"));
			//	����	��ȯ���� Map�� ��� Key����
			//			�ڵ������� AS�� ����� ������ Ű������ ��ϵȴ�.
			//			���� AS�� ������� ������ �ʵ��̸��� Ű������ ��ϵȴ�.
		}
		mv.setViewName("Member/LoginForm");
		return mv;
	}
	
	/*
	 * �α׾ƿ� ��û �Լ�
	 */
	@RequestMapping("/Member/LoginOut")
	public ModelAndView loginOut(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	����
		//		������ �� ���̰� �α��� ������ ������.
//		session.removeAttribute("ID");
//		session.removeAttribute("NAME");
//		session.removeAttribute("NICK");
		session.invalidate();
		//	����
		//		������ �� �κп��� �ٸ������� ����ߴ� ��� ������ ���� ���־� �Ѵ�.
		mv.setViewName("Member/LoginForm");
		return mv;
	}
}



package com.isundol.dol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SmartController {
	/*
	 * 	글쓰기 폼 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardWriteForm")
	public ModelAndView		boardWriteForm() {
		ModelAndView		mv = new ModelAndView();
		
		
		mv.setViewName("Smart/BoardWriteForm");
		return mv;
	}
}



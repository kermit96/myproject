package com.isundol.dol.controller;

import 	java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import 	javax.servlet.http.HttpSession;

import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import 	org.springframework.web.servlet.ModelAndView;
import 	org.springframework.web.servlet.view.RedirectView;

import 	com.isundol.dol.dao.FileUploadDAO;
import 	com.isundol.dol.data.FileUploadData;
import 	com.isundol.dol.util.FileUtil;

@Controller
public class FileUploadController {

	@Autowired
	FileUploadDAO		fDao;
	/*	
	 * 	일반 파일 업로드 폼 요청
	 */
	@RequestMapping("/FileUpload/SingleForm")
	public ModelAndView	SingleForm() {
		ModelAndView		mv = new ModelAndView();
		
		mv.setViewName("FileUpload/SingleForm");
		return mv;
	}
	
	/*
	 * 	일반 파일 업로드 요청
	 */
	@RequestMapping("/FileUpload/SingleUpload")
	public ModelAndView	SingleUpload(FileUploadData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	전송되어온 파라메터 받고(임시 메모리에 저장이 되었다.)
		//	문제는 아직 사용자가 원하는 위치에 파일이 저장되지는 않았다.
		//	단점	사용자가 직접 임시 메모리에 저장된 파일을
		//			원하는 위치에 넣어주어야 한다.
		//	1.	저장할 위치를 정한다.
		//		저장할 위치는		일반 위치		파일 다운로드만 가능하고
		String	path = "D:\\FileUpload";
		//							서버 위치		웹에서 사용할 수 있다.
		//											(상품 이미지를 업로드하고 상품 이미지를 웹에서 보여주고 싶으면...)
//		String path = session.getServletContext().getRealPath("images");
		//	2.	저장할 파일의 이름을 구한다.
		//		이 이름은 마음대로 정하면 된다.
		//		하지만 대부분 업로드된 파일 이름을 그대로 사용하기를 원하므로.......
		String		oriName = "";
		String		saveName = "";
		long		length = 0;
		if(!data.upfile.isEmpty()) {
			oriName 	= data.upfile.getOriginalFilename();
			saveName = FileUtil.rename(path, oriName);
			length = data.upfile.getSize();
		//	참고
		//		getOriginalFilename();		업로드된 파일 이름을 구한다.
		//		getSize();					업로드된 파일의 크기를 구한다.
		//		isEmpty();					업로드된 파일의 존재 여부를 구한다.
		//	3.	이 두가지 정보를 이용해서 File 클래스를 만든다.
			File		file = new File(path, saveName);
			try {
				//	4.	원하는 위치로 파일을 저장한다.
				data.upfile.transferTo(file);
			}
			catch(Exception e) {
				System.out.println("파일 업로드 실패 " + e);
			}
		}
		//	5.	업로드된 파일을 데이터베이스에 그 정보를 기억한다.
		data.len = length;
		data.oriname = oriName;
		data.savename = saveName;
		data.path = path;
		fDao.insertBoard(data);
		//	뷰를 부른다.
		RedirectView	rv = new RedirectView("../FileUpload/SingleForm.dol");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	다중 파일 업로드 폼 요청 처리 함수
	 */
	@RequestMapping("/FileUpload/MultiForm")
	public ModelAndView	MultiForm() {
		ModelAndView		mv = new ModelAndView();
		
		mv.setViewName("FileUpload/MultiForm");
		return mv;
	}
	
	/*
	 * 	다중 파일 업로드 요청 처리 함수
	 */
	@RequestMapping("/FileUpload/MultiUpload")
	public ModelAndView	MultiUpload(FileUploadData data) {
		ModelAndView		mv = new ModelAndView();
		//	넘어온 파일을 원하는 위치에 옮긴다.
		String	path = "D:\\FileUpload";
		for(int i = 0; i < data.files.length; i++) {
			//	만약 파일이 첨부되지 않았으면 파일을 옮기는 작업을 하지 말아야 한다.
			if(data.files[i].isEmpty()) {
				//	그 첨부파일은 존재하지 않니?
				continue;
			}
			//	하나씩 파일의 이름을 구하자.
			String	filename = data.files[i].getOriginalFilename();
			filename = FileUtil.rename(path, filename);
			File		file = new File(path, filename);
			try {
				data.files[i].transferTo(file);
			}
			catch(Exception e) {
				System.out.println("파일 업로드 에러 = " + e);
			}
		}
		
		RedirectView	rv = new RedirectView("../FileUpload/MultiForm.dol");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	목록보기 요청 처리 함수
	 */
	@RequestMapping("/FileUpload/BoardList")
	public ModelAndView	boardList() {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		데이터베이스에 있는 목록 꺼내서
		ArrayList	list = fDao.selectBoard();
		//		뷰에게 알려준다.
		mv.addObject("LIST", list);
		mv.setViewName("FileUpload/BoardList");
		return mv;
	}
	
	/*
	 * 	파일 다운로드 요청 처리 함수
	 */
	@RequestMapping("/FileUpload/FileDownload")
	public ModelAndView		fileDownload(HttpServletRequest req) {
		ModelAndView		mv = null;
		//	할일
		//		넘어온 파라메터 받는다
		String	strNo = req.getParameter("oriNo");
		int		oriNo = Integer.parseInt(strNo);
		//		다운로드 횟수를 증가시킨다.
		fDao.updateDownHit(oriNo);
		//		정보를 꺼낸다.
		FileUploadData	data = fDao.selectDownInfo(oriNo);
		
		//		파일 다운로드 뷰를 부른다.
		//	파일 다운로드 뷰에게 다운로드할 정보는
		//	반드시 Map으로 만들어서 넘겨주기로 약속했다.
		Map	map = new HashMap();
		map.put("oirname", data.oriname);
		File	file = new File("D:\\FileUpload", data.savename);
		map.put("downloadFile", file);
		//	★★★
		//	다운로드의 뷰이름은 DI로 등록된 id값이다.
		mv = new ModelAndView("download", map);
		return mv;
	}
}
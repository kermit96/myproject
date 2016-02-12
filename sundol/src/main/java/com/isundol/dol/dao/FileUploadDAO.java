package com.isundol.dol.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Repository;

import com.isundol.dol.data.FileUploadData;

@Repository
public class FileUploadDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 	게시물 등록 처리 함수
	 */
	public void insertBoard(FileUploadData data) {
		sqlSession.insert("fileupload.insertBoard", data);
	}
	
	/*
	 * 	게시물 목록 처리 함수
	 */
	public ArrayList	selectBoard() {
		return (ArrayList) sqlSession.selectList("fileupload.selectBoard");
	}
	/*
	 * 	다운로드 회수 증가 처리 함수
	 */
	public void updateDownHit(int NO) {
		sqlSession.update("fileupload.updateDownHit", NO);
	}
	/*
	 * 	다운로드 파일 정보 검색 함수
	 */
	public FileUploadData selectDownInfo(int NO) {
		return (FileUploadData) sqlSession.selectOne("fileupload.selectDownload", NO);
	}
}









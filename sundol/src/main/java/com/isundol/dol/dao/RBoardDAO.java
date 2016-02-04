package com.isundol.dol.dao;

import java.util.ArrayList;
import java.util.HashMap;

import 	org.mybatis.spring.SqlSessionTemplate;
import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Repository;

import com.isundol.dol.data.RBoardData;

@Repository
public class RBoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 	총 데이터 갯수 구하기 함수
	 * 	총 데이터 개수를 구하는 곳은 전체 목록에서 한번
	 * 	검색 목록에서 한번 있다.
	 * 
	 * 	우리는 약속하자.
	 * 		전체 목록은		1번으로 하고
	 * 		검색 목록은		2번으로 한다.
	 */
	public int getTotal(int kind) {
		//	DAO 함수의 매개변수는 parameterType하고는 전혀 무관하다.
		//	다만 그 질의를 실행하기 위해서 필요한 모든 데이터를 넘겨주는 역활을 한다.
		//	여기서는 동일한 형태의 질의 명령이 2개 존재할 것인데.....
		//	그러면 원칙은 함수를 2개 만들어야 한다.
		//	하지만		DAO함수와 질의 명령은 절대 1:1 이 아니다.
		//	이 함수의 매개변수는 질의 명령과는 무관하다.
		
		//	매개변수의 역활
		//		총 데이터개수를 구하는데... 어떤것을 구할지를 알려주는 기호
		if(kind == 1) {
			return sqlSession.selectOne("rboard.gettotal");
		}
		else {
			return 0;
		}
	}
	/*
	 *	목록보기 요청 함수 
	 */
	public ArrayList	getBoardList() {
		//	DAO의 반환값은 resultType과 동일한것이 아니고 유관하다.
		//	즉	질의의 실제 결과는 여러줄이 나올 가망성이 있으므로
		//	질의 결과는 여러줄을 기억하는 방식으로 처리해야 한다.
		ArrayList	result = (ArrayList)sqlSession.selectList("rboard.getlist");
		return result;
		//	즉 질의를 던진 실제 결과가 여러줄이 나올 수 있는 가망성이 있으면
		//	여러줄을 기억하도록 처리해 주어야 한다.
		//	만약 여러줄이 나오는데 한줄만 처리하도록 하면.... 에러가 발생한.
	}
	
	/*
	 *	원글 등록하기 요청 함수
	 */
	public void insertBoard(RBoardData data) {
		sqlSession.insert("rboard.boardinsert", data);
	}
	
	/*
	 * 	봤던 글번호 검색 함수
	 * 	참고
	 * 		#{ID}는 키값을 이용해서 처리한다.
	 * 		즉		Map이면 맵에 등록된 키값이 ID이면 되고
	 * 				데이터빈이면 getXXX 함수가 ID이면 된다.
	 * 	★★
	 * 	만약 parameterType이 한개인 경우는 변수 이름이 키값이 된다.
	 */
	public HashMap getShowno(String ID) {
		return sqlSession.selectOne("rboard.showno", ID);
	}
	
	/*
	 * 	글번호 수정 및 등록 함수
	 * 	SQL 명령과 DAO는 반드시 1:1이 될 필요가 없다.
	 * 	비슷한 질의 명령은 하나의 함수를 이용해서 처리해도 무방하다.
	 */
	public void updateShowno(HashMap map, int kind) {
		//	kind가 1이면 update, 2이면 insert 를 실행하도록 한다.
		if(kind == 1) {
			sqlSession.update("rboard.updateshowno", map);
		}
		else {
			sqlSession.insert("rboard.insertshowno", map);
		}
	}

	/*
	 * 		조회수 증가 질의 실행 함수
	 */
	public void updateHit(int NO) {
		sqlSession.update("rboard.updatehit", NO);
	}
	/*
	 * 	상세보기 요청 질의 실행 함수
	 */
	public RBoardData selectView(int NO) {
		return sqlSession.selectOne("rboard.boardview", NO);
	}
	
	/*
	 * 	댓글등록 질의 요청 함수
	 */
	public void insertReple(RBoardData data) {
		sqlSession.insert("rboard.insertreple", data);
	}
	
	/*
	 * 	댓글 검색 질의 요청 함수
	 */
	public ArrayList	getReple(int NO) {
		return (ArrayList)sqlSession.selectList("rboard.selectreple", NO);
	}
	
	/*
	 * 수정 혹은 삭제가 가능한 질의 요청 함수
	 */
	public int isUpdate(HashMap map) {
		return sqlSession.selectOne("rboard.isupdate", map);
	}
	
	/*
	 * 	게시물 수정 함수
	 */
	public void updateBoard(RBoardData data) {
		sqlSession.update("rboard.updateboard", data);
	}
	
	/*
	 * 	게시물 삭제 함수
	 */
	public void deleteBoard(int NO) {
		sqlSession.update("rboard.deleteboard", NO);
	}
	
	/*
	 * 	추천 수정 함수
	 */
	public void updateGood(int NO) {
		sqlSession.update("rboard.updategood", NO);
	}

	/*
	 * 	추천 상황 검색
	 */
	public int selectGood(int NO) {
		return sqlSession.selectOne("rboard.selectgood", NO);
	}
}










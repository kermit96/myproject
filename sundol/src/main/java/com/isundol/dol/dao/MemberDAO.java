package com.isundol.dol.dao;

import 	java.util.HashMap;

import 	org.mybatis.spring.SqlSessionTemplate;
import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Repository;

//	이 클래스가 자동 DI가 되기 위해서 어노테이션 시킨다.
@Repository
public class MemberDAO {
	//	이 클래스는 컨넥션 풀을 사용해야 하는데
	//	우리는 컨넥션풀을 사용하기 위한 클래스를 DI로 등록해 놓았다.
	//	고로 그 클래스를 이용하도록 한다.
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 	로그인 처리를 위한 함수
	 */
	public HashMap login(HashMap map) {
		//	매개변수는 parameterType과 무관하다.
		//	이것은 컨트롤러가 제공하는 데이터 형태를 의미하는 것이다.
		
		//	1번 파라메터는 필수사항 무조건 실행할 SQL 코드
		//		SQL 코드		namespace.id
		//	2번 파라메터는 선택사항으로 필요한 데이터를 제공한다.
		HashMap 	result = sqlSession.selectOne("member.loginproc", map);
		//	노파심
		//		질의 명령에 필요한 데이터를 제공하는 방식은
		//		SQL을 작성할 때 사용했던 parameterType과 같아야 한다.
		//		반환값은
		//		SQL을 작성할 때 사용했던 resultType의 의존한다.
		//		즉, 반드시 같지 않아도 상관은 없는데... 다만
		//		resutType을 지정한 것이 분명히 그 안에 포함될 수 있어야 한다.
		return result;
	}
}








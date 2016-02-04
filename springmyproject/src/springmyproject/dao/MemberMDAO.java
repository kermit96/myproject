package springmyproject.dao;

import java.util.HashMap;

public interface MemberMDAO {
//	지금 만든 로그인용 질의 명령을 실행할 함수를 정의한다.
	public HashMap loginProc(HashMap map);
	//	앞으로 다른 질의 명령이 존재하면 그 질의 명령을 실행할 함수를 계속해서
	//	정의해 주면 된다.
	public int getCount();
}

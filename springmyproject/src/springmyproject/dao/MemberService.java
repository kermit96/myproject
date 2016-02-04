package springmyproject.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MemberService  extends SqlSessionDaoSupport implements MemberMDAO {
	public MemberService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public HashMap loginProc(HashMap map) {
		// TODO Auto-generated method stub


		SqlSession session  = getSqlSession();
 
	     HashMap result = (HashMap) session.selectOne("member.LOGIN",map );     
		  	return result;
		  	
	}
	@Override
	public int getCount() {
		int	count = this.getSqlSession().selectOne("member.CCC");
		return count;
	}
}

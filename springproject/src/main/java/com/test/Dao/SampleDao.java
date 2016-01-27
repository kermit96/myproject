package com.test.Dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class SampleDao extends SqlSessionDaoSupport {
  
	public int getCount() {
		int	count = this.getSqlSession().selectOne("member.CCC");
		return count;
	}   
}

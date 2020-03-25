package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int update(SiteVo siteVo) {
		int count = sqlSession.update("site.update", siteVo);
		return count;
	}

	public SiteVo find() {
		SiteVo vo =  sqlSession.selectOne("site.find");
		return vo;
	}
	
}

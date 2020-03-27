package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {		
		return sqlSession.insert("board.insert", vo) == 1;		
	}
		
	public boolean delete(long no) {
		return sqlSession.delete("board.delete", no) == 1;		
	}
	
	public int update(BoardVo vo) {
		return sqlSession.update("board.modify", vo);
	}		
	
	public int updateHit(Long no) {
		return sqlSession.update("board.updateHit", no);
	}


	public BoardVo findNo(long no) {
		return sqlSession.selectOne("board.findNo", no);
	}

	public List<BoardVo> findAll(String kwd) {		
		List<BoardVo> list = sqlSession.selectList("board.findAll", kwd);
		return list;			
	}

	public boolean updateOno(BoardVo vos) {
		return sqlSession.update("board.updateOno", vos) == 1;
	}

	public boolean insertReply(BoardVo vo) {
		return sqlSession.insert("board.insertReply", vo) == 1;
	}



}

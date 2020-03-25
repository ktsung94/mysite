package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getList(String kwd) {
		return boardRepository.findAll(kwd);
	}

	public boolean addBoard(BoardVo vo) {
		return boardRepository.insert(vo);		
	}

	public BoardVo findByNo(Long no) {
		BoardVo boardVo = boardRepository.findNo( no );
		
		if( boardVo != null ) {
			boardRepository.updateHit( no );
		}
		
		return boardVo;
	}

	public boolean modify(BoardVo vo) {
		return boardRepository.update(vo) == 1;
	}

	public boolean delete(Long no) {
		return boardRepository.delete(no);
	}

	public void replyBoard(BoardVo vo, Long no) {
				
	}
}

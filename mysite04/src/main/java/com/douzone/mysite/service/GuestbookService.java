package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookRepository guestbookRepository;

	public boolean insert(GuestbookVo vo) {
		return guestbookRepository.insert(vo);
	}

	public List<GuestbookVo> getList() {
		
		return guestbookRepository.findAll();
	}

	public boolean delete(Long no, String password) {
		return 1== guestbookRepository.delete(new GuestbookVo(no, password));		
	}

	public List<GuestbookVo> getMessageList(Long startNo) {
		return guestbookRepository.findAll(startNo);		
	}	
	
}

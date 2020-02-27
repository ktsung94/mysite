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

	public void delete(GuestbookVo vo) {
		// TODO Auto-generated method stub
		
	}

	public boolean delete(Long no, String password) {
		return guestbookRepository.delete(no, password);		
	}	
	
}

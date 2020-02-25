package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
		
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(GuestbookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook/list";
	}

	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable("no") Long no) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@RequestParam(value="password") String password, @PathVariable("no") Long no) {
		if(guestbookService.delete(no, password))
			return "redirect:/guestbook/list";
		else
			return "guestbook/delete/no";
	}
	
}

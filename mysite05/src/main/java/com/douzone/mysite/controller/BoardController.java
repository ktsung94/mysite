package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(
			@RequestParam(value="page", required=true, defaultValue="1") int page,
			@RequestParam(value="kwd", required=true, defaultValue="") String kwd,
			Model model) {
		List<BoardVo> list = boardService.getList(kwd);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("kwd", kwd);
		System.out.println(page);
		System.out.println("kwd:" + kwd);
		return "board/list";
	}

	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(HttpSession session) {
		System.out.println("writeget");
		return "board/write";
	}

	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardService.addBoard(vo);
		System.out.println("writepost");
		return "redirect:/board";
	}

	@RequestMapping(value="/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.findByNo(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}")
	public String modify(HttpSession session, @PathVariable( "no" ) Long no, Model model) {
			UserVo authUser = (UserVo)session.getAttribute("authUser");

			BoardVo vo = boardService.findByNo(no);
			model.addAttribute( "vo", vo );
			return "board/modify";
	}

	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		boardService.modify(vo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/delete/{no}")
	public String delete(HttpSession session, @PathVariable( "no" ) Long no) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		boardService.delete(no);
		return "redirect:/board";
	}
	
	// reply도 @auth사용가능
	@Auth
	@RequestMapping(value="/reply/{no}", method=RequestMethod.GET)
	public String reply(@AuthUser UserVo authUser) {
		return "board/reply";
	}

	@Auth
	@RequestMapping(value="/reply/{no}", method=RequestMethod.POST)
	public String reply(@AuthUser UserVo authUser, @PathVariable( "no" ) Long no, BoardVo vo) {
		boardService.replyBoard(vo, no);
		System.out.println("replypost");
		return "redirect:/board";
	}
}

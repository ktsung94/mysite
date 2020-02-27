package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		userService.join(vo);
		// 프라이머리키를 가져온다. joinDate는 가져올수없다.
		// 가져오려면 다시 쿼리문을 짜야함
		System.out.println(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {		
		return "user/login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@AuthUser UserVo authUser, UserVo vo) {
		return "redirect:/";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		//UserVo authUser = (UserVo)session.getAttribute("authUser");
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String handleException() {
//		return "error/exception";
//	}
}


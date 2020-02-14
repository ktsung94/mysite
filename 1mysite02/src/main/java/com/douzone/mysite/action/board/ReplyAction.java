package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 접근제어(Access Control List, ACL)
		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String userNo = request.getParameter("userNo");
		String gNo = request.getParameter("gNo");
		String oNo = request.getParameter("oNo");
		String depth = request.getParameter("depth");

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(Long.parseLong(userNo));
		vo.setDepth(Integer.parseInt(depth));

		new BoardRepository().updateOno(Integer.parseInt(gNo), Integer.parseInt(oNo));
		
		new BoardRepository().insertReply(vo, Integer.parseInt(gNo), Integer.parseInt(oNo), Integer.parseInt(depth));

		response.sendRedirect(request.getContextPath() + "/board?a=list&page=1");		

	}

}

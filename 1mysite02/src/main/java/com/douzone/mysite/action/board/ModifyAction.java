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

public class ModifyAction implements Action {

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

		if(!request.getParameter("no").equals(null)) {
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");

			BoardVo vo = new BoardVo();
			vo.setNo(Long.parseLong(no));
			vo.setTitle(title);
			vo.setContents(contents);

			new BoardRepository().update(vo);

			response.sendRedirect(request.getContextPath() + "/board?a=list&page=1");		
		}
		else
			response.sendRedirect(request.getContextPath());		
	}

}

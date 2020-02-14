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

public class ModifyFormAction implements Action {

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

		String no = request.getParameter("no");

		BoardVo vo = new BoardRepository().findNo(Long.parseLong(no));

		request.setAttribute("vo", vo);

		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);

	}

}

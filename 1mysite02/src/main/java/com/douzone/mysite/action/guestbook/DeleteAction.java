package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.web.action.Action;

public class DeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");

		if(new GuestbookRepository().delete(Long.parseLong(no), password))
			response.sendRedirect(request.getContextPath() + "/guestbook?a=list");
		else
			response.sendRedirect(request.getContextPath() + "/guestbook?a=deleteform&no=" + no);
		
	}

}

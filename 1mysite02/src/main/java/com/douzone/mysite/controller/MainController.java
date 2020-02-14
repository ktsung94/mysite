package com.douzone.mysite.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.action.main.MainActionFactory;
import com.douzone.web.action.Action;

public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() called");
		
		request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
		Action action = new MainActionFactory().getAction(actionName);
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() called");
		doGet(request, response);
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service() called");
		super.service(arg0, arg1);
	}

	@Override
	public void destroy() {
		System.out.println("destory() called");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		String configPath = this.getServletConfig().getInitParameter("config");
		System.out.println("init() called: " + configPath);
		Map<String, Object> map = new HashMap<>();	// Servlet은 지역변수를 사용하면 안되므로...
		this.getServletContext().setAttribute("cache", map);
		super.init();
	}
	
}

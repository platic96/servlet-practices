package com.saltlux.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.mysite.dao.GuestDao;
import com.saltlux.mysite.vo.GuestVo;
import com.saltlux.web.mvc.WebUtill;

/**
 * Servlet implementation class GuestServlet
 */
public class GuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");
		
		if("deleteform".equals(action)) {
			WebUtill.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);	
		}
		else if("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			GuestVo vo = new GuestVo();

			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestDao().Insert(vo);

			response.sendRedirect(request.getContextPath()+"/guestbook?a=index");
		}
		else if("delete".equals(action)) {
			String no = request.getParameter("no");
			int number = -1;
			if(no!= null && no.matches("\\d*"))
			number = Integer.parseInt(no);
			String password = request.getParameter("password");
			new GuestDao().Delete(number, password);
			
			response.sendRedirect(request.getContextPath()+"/guestbook?a=index");
		}
		else {
			
			List<GuestVo> list = new GuestDao().findAll();
			
			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/guestbook/index.jsp");
			rd.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

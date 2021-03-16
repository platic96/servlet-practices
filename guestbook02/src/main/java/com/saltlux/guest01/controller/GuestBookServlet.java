package com.saltlux.guest01.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.guest01.dao.GuestDao;
import com.saltlux.guest01.vo.GuestVo;


/**
 * Servlet implementation class GuestBookServlet
 */
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("a");
		
		if("deleteform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		}
		else if("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");

			GuestVo vo = new GuestVo();

			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestDao().Insert(vo);

			response.sendRedirect(request.getContextPath()+"/el");
		}
		else if("delete".equals(action)) {
			String no = request.getParameter("no");
			int number = -1;
			if(no!= null && no.matches("\\d*"))
			number = Integer.parseInt(no);
			String password = request.getParameter("password");
			
			new GuestDao().Delete(number, password);
			
			response.sendRedirect(request.getContextPath()+"/el");
		}
		else {
			
			List<GuestVo> list = new GuestDao().findAll();
			
			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

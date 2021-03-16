package com.saltlux.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.mysite.dao.BoardDao;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.web.mvc.WebUtill;

/**
 * Servlet implementation class BoardServlet
 */
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		

		String action = request.getParameter("a");
		if ("writeform".equals(action)) {
			if(request.getParameter("answer").equals("true")) {
				request.setAttribute("no", request.getParameter("no"));
			}
			request.setAttribute("answer", request.getParameter("answer"));
			WebUtill.forward("WEB-INF/views/board/writeform.jsp", request, response);
		} else if ("view".equals(action)) {
			String no = request.getParameter("no");
			int number = -1;
			if (no != null && no.matches("\\d*"))
				number = Integer.parseInt(no);

			BoardVo vo = new BoardDao().find(number);

			request.setAttribute("vo", vo);

			WebUtill.forward("WEB-INF/views/board/view.jsp", request, response);
		} else if ("modify".equals(action)) {
			String no = request.getParameter("no");
			int number = -1;
			if (no != null && no.matches("\\d*"))
				number = Integer.parseInt(no);
			String title = request.getParameter("title");
			String context = request.getParameter("context");

			BoardVo vo = new BoardVo();
			vo.setNo(number);
			vo.setTitle(title);
			vo.setContext(context);

			request.setAttribute("vo", vo);

			WebUtill.forward("WEB-INF/views/board/modify.jsp", request, response);
			
		} else if ("update".equals(action)) {
			String no = request.getParameter("no");
			int number = -1;
			if (no != null && no.matches("\\d*"))
				number = Integer.parseInt(no);

			String title = request.getParameter("title");
			String context = request.getParameter("content");

			BoardVo vo = new BoardVo();
			vo.setNo(number);
			vo.setTitle(title);
			System.out.println(context);
			vo.setContext(context);

			new BoardDao().UpdateData(vo);

			WebUtill.redirect(request.getContextPath() + "/board", request, response);
		} else if("delete".equals(action)) {
			WebUtill.redirect(request.getContextPath() + "/board", request, response);
		}
		else if("write".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String user_no = request.getParameter("user_no");
			String no = request.getParameter("no");
			boolean answer = false;
			answer = Boolean.parseBoolean(request.getParameter("answer"));
			int number = -1;
			if (user_no != null && user_no.matches("\\d*"))
				number = Integer.parseInt(user_no);
			
			BoardVo vo = new BoardVo();
			if(request.getParameter("answer").equals("true")){
				vo.setNo(Integer.parseInt(no));
			}
			vo.setUser_no(number);
			vo.setTitle(title);
			vo.setContext(content);
			new BoardDao().InsertData(vo, answer);
			WebUtill.redirect(request.getContextPath()+"/board", request, response);
		}
		else {
			List<BoardVo> list = new BoardDao().findAll();

			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			WebUtill.forward("WEB-INF/views/board/index.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

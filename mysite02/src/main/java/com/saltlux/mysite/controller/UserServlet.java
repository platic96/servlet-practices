package com.saltlux.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saltlux.mysite.dao.UserDao;
import com.saltlux.mysite.vo.UserVo;
import com.saltlux.web.mvc.WebUtill;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("a");

		if ("joinform".equals(action)) {
			WebUtill.forward("/WEB-INF/views/user/joinform.jsp", request, response);
		} else if ("logout".equals(action)) {
			HttpSession session = request.getSession();

			// 로그아우 ㅅ처리
			if (session != null && session.getAttribute("authUser") != null) {
				session.removeAttribute("authUser");
				session.invalidate();
			}
			WebUtill.redirect(request.getContextPath(), request, response);

		} else if ("joinsuccess".equals(action)) {
			WebUtill.forward("/WEB-INF/views/user/joinsuccess.jsp", request, response);
		} else if ("loginform".equals(action)) {
			WebUtill.forward("/WEB-INF/views/user/loginform.jsp", request, response);
		} else if ("updateform".equals(action)) {
			//접근 제어 (Access Control)
			HttpSession session = request.getSession();
			if(session == null) {
				WebUtill.redirect(request.getContextPath(), request, response);
				return;
			}
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {
				WebUtill.redirect(request.getContextPath(), request, response);
				return;
			}
			
			Long no = authUser.getNo();
			//UserVo userVo = new UserDao().findByNo(no);
			
			//request.setAttribute("userVo", userVo);
			WebUtill.forward("/WEB-INF/views/user/updateform.jsp", request, response);
			
		} else if ("join".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);

			new UserDao().insert(userVo);

			WebUtill.redirect(request.getContextPath() + "/user?a=joinsuccess", request, response);

		} else if ("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserVo vo = new UserVo();
			vo.setEmail(email);
			vo.setPassword(password);

			UserVo authUser = new UserDao().findByEmailAndPassword(vo);

			if (authUser == null) {
				request.setAttribute("authResult", "fail");
				WebUtill.forward("/WEB-INF/views/user/loginform.jsp", request, response);
				return;
			}

			// 인증처리
			HttpSession session = request.getSession(true);
			session.setAttribute("authUser", authUser);

			// 응답
			WebUtill.redirect(request.getContextPath(), request, response);
		} else {
			WebUtill.redirect(request.getContextPath(), request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

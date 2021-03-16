package com.saltlux.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.web.mvc.WebUtill;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doget() called");
		request.setCharacterEncoding("utf-8");
		int visitCount = 0;
		
		getServletContext().setAttribute(getServletName(), response);
		
		//쿠키 읽기
		Cookie[] cookies = request.getCookies();
		if(cookies  != null && cookies.length>0) {
			for(Cookie cookie : cookies) {
				if("visitCount".equals(cookie.getName())) {
					visitCount = Integer.parseInt(cookie.getValue());
				}
			}
		}
		
		visitCount++;
		Cookie cookie = new Cookie("visitCount",String.valueOf(visitCount));
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24*60*60); //1 day
		
		response.addCookie(cookie);
//			
		WebUtill.forward("/WEB-INF/views/main/index.jsp", request, response);
	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		System.out.println("destroy() called!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		super.destroy();
//	}
//
//	@Override
//	public void init() throws ServletException {
//		// TODO Auto-generated method stub
//		System.out.println("init() called");
//		super.init();
//	}
//
//	@Override
//	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		System.out.println("survice() called");
//		super.service(req, res);
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

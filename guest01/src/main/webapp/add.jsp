<%@page import="com.saltlux.guest01.dao.GuestDao"%>
<%@page import="com.saltlux.guest01.vo.GuestVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String contents = request.getParameter("contents");

	GuestVo vo = new GuestVo();

	vo.setName(name);
	vo.setPassword(password);
	vo.setContents(contents);
	new GuestDao().Insert(vo);

	response.sendRedirect("/guestbook01");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>등록되었습니다.</h3>
</body>
</html>
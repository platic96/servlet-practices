<%@page import="com.saltlux.guest01.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("no");
	int number = -1;
	if(no!= null && no.matches("\\d*"))
	number = Integer.parseInt(no);
	String password = request.getParameter("password");
	
	new GuestDao().Delete(number, password);
	
	response.sendRedirect("/guestbook01");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
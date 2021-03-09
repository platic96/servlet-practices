<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	int number = -11;
	if(no!= null && no.matches("\\d*"))
		number = Integer.parseInt(no);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hellow World02</h1>
	<a href='/helloweb/tag.jsp' target='_blank'>태그 연습하기</a>
	<h2>넘어온 값은 </h2>
	<%=number+10%>
</body>
</html>
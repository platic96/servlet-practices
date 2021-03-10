<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String no = request.getParameter("no");
	int number = -1;
	if(no!= null && no.matches("\\d*"))
		number = Integer.parseInt(no);
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form method="post" action="/guestbook01/delete.jsp">
		<input type='hidden' name="no" value="<%=number%>">
		
		<table>
			<tr>
				<tb><%=number %></tb>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><input type="submit" value="확인"></td>
				<td><a href="">메인으로 돌아가기</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/helloweb/Join" method="post">
		이메일 : <input type="text" name="email"value=""/>
		<br/><br/>
		비밀번호 : <input type="password" name="password"value=""/>
		<br/><br/>
		생년 : 
		<select name="birthyear">
			<option value="1994">1994년</option>
			<option value="1995">1995년</option>
			<option value="1996">1996년</option>
		</select>
		<br/><br/>
		
		성별:
		여<input type="radio" name="gender" value="female" checked="checked"/>
		남<input type="radio" name="gender" value="male"/>
		<br/><br/>

		취미:
		코딩<input type="checkbox" name="hobbies" value="coding">
		수영<input type="checkbox" name="hobbies" value="swimming">
		낚시<input type="checkbox" name="hobbies" value="fishing">
		요리<input type="checkbox" name="hobbies" value="cooking">
		<br/><br/>
		
		자기소개:
		<textarea name="desc"></textarea>		
		<br/><br/>
		
		<input type="submit" value="가입" />
	</form>
</body>
</html>
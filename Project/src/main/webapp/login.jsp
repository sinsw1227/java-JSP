<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="Login">
		id : <input type="text" name="id"> <br>
		passwd : <input type="password" name="passwd"> <br>
		<button type="submit">로그인</button>
	</form>
	${err }
	<a href="Regist">regist</a>
</body>
</html>
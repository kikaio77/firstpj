<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="${pageContext.request.contextPath}/member/login" method="post">
		<ul>
			<li><label for="id">id : </label><input type="text" id="id" name="id" /></li>
			<li><label for="password">password : </label><input type="password" name="password" id="password" /> </li>
			<li><button type="submit">로그인</button><button type="reset">리셋</button></li>
		</ul>
	</form>
	<a href="${pageContext.request.contextPath}/member/signUp">회원가입</a>
</body>
</html>
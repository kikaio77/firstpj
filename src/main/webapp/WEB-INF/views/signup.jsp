<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form:form modelAttribute="memberVO" action="${pageContext.request.contextPath}/member/signupResult">
		id : <form:input path="id"/><br />
		password : <form:password path="password"/><br />
		name : <form:input path="name"/><br />
		성별 : <label>남성<form:radiobutton path="gender" value="M"/></label>
			<label>여성<form:radiobutton path="gender" value="F"/></label><br />
		email : <form:input path="email"/><br />
		phone : <form:input path="phone"/><br />
		<button>전송</button>
	</form:form>
</body>
</html>
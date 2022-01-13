<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>글쓰기 페이지</h1>
	<form:form modelAttribute="BBSVO" action="${pageContext.request.contextPath}/bbs/create_result" >
		<ul>
			<li><label for="title">제목 : </label><form:input path="title"/></li>
			<li><form:textarea path="content" cols="50" rows="10"/></li>
			<form:hidden path="ownerid" value="${sessionScope.account.id}"/>
			<form:hidden path="ownername" value="${sessionScope.account.name}"/>
			<form:hidden path="filelist"/>
		</ul>
	</form:form>
	
	<label for="upload">파일 추가 : </label><input type="file" name="upload" id="upload" multiple/>
	<button id="submit">전송</button>
	
	<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				const formData = new FormData();
				const $upload = $("#upload");
				let files = $upload[0].files;
				
				for (var i = 0; i < files.length; i++) {
					formData.append("uploadFile", files[i])	
				}
				
				$.ajax({
					url : '${pageContext.request.contextPath}/bbs/uploadfile',
					processData : false,
					contentType : false,
					data : formData,
					type : "post",
					datatype : "json",
					success: function(result){
						//console.log(JSON.stringify(result));
						$("#filelist").val(JSON.stringify(result));
						$("#BBSVO").submit();
					}
				});
			});
		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>bbs 상세 페이지</h1>
	<h1>제목 : ${bbsVO.title}</h1>
	<h3>글쓴이 : ${bbsVO.ownername}</h3>
	<hr />
	<h3>내용 : </h3>
	<p>${bbsVO.content}</p>
	<hr />
	<c:forEach var="file" items="${filelist}">
		<a href="${pageContext.request.contextPath}/downloadFile/${file.localName}/${file.serverName}">${file.localName}</a><br>
	</c:forEach>
	<hr />
	<c:if test="${sessionScope.account.id == bbsVO.ownerid }">
		<button id="modify">수정</button>
		<button id="delete">삭제</button>
	</c:if>
	<%-- <a href="${pagaContext.request.contextPath}/bbs/modify_bbs?num=${bbsVO.num}">수정</a>
	<a href="${pagaContext.request.contextPath}/bbs/delete_bbs?num=${bbsVO.num}">삭제</a> --%>
	
	<script type="text/javascript">
		$(function(){
			$("#delete").click(function(){
				if(confirm("정말로 삭제하시겠습니까")){
					location.href = "${pageContext.request.contextPath}/bbs/delete_bbs?num=${bbsVO.num}";
				}
			});
			$("#modify").click(function(){
				location.href ="${pageContext.request.contextPath}/bbs/modify_bbs?num=${bbsVO.num}";
			});
		});
	</script>
</body>
</html>
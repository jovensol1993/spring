<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글게시판</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
 	$(document).ready(function(){
		$('#frm').submit();
	});
 
</script>

</head>
<body>
	 
 	<form method="POST" action="/cls${URI}" id="frm">
		<input type="hidden" name="nowPage" value="${param.nowPage}">
	</form>
	 
	<div>${param.nowPage}</div>
	<div class="w3-col w3-red" style="height:200px;"></div>
 <!-- 	 
	 <c:redirect url="/reBoard/reBoardList.cls">
	 	<c:param name="nowPage" value="${param.nowPage}" />
	 </c:redirect>
 -->
</body>
</html>
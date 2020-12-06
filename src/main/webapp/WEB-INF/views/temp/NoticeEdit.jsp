<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Board List</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/cls/js/temp/notice.js"></script>

</head>
<body>
	<div class="w3-content mw800">
		<!-- top -->
		<div class="w3-col w3-margin-top w3-padding w3-card-4">
			<form method="POST" id="frm">
				<input type="hidden" name="nno" value="${nVO.nno}">
				<div class="w3-col w3-border w3-margin-top w3-center">
					<div class="w3-col m1 w3-light-grey w3-border-right">번호</div>
					<div class="w3-col m11 w3-light-grey w3-border-right">내용</div>
				</div>

				<div class="w3-col w3-border-left w3-border-right w3-margin-bottom">
					<div class="w3-col w3-center w3-border-bottom">
						<div class="w3-col m1 w3-border-right w3-margin-top">${nVO.nno}</div>
						<div class="w3-col m11 w3-margin-top">
							<input type="text" class="w3-col w3-border-right w3-margin-top"
								id="contents" name="contents" value="${nVO.contents}">
						</div>
					</div>
				</div>
			</form>
			<div class="w3-col m1 w3-btn w3-border" id="ubtn">수정완료</div>
		</div>
	</div>
</body>
</html>
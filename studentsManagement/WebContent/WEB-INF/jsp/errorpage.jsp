<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
</head>
<body>
	<h1>エラー！</h1>
	<button onclick="location.href='<%= contextPath %>/index'">TOPへ戻る</button>
</body>
</html>
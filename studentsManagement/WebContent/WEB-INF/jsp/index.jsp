<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生徒名簿管理システム</title>
</head>
<body>
	<h1>生徒名簿管理</h1>
	<button onclick="location.href='<%= contextPath %>/allstudentlist'">生徒一覧</button><br>
	<button onclick="location.href='<%= contextPath %>/newstudentregistration'">新規登録</button>
</body>
</html>
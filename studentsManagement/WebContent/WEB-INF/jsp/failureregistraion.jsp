<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生徒情報登録 - 失敗</title>
</head>
<body>
	<h1>登録失敗</h1>
	<p>生徒情報の登録に失敗しました</p>
	<p>お手数ですが、再度やり直しをお願いします。</p>

	<a href="<%= contextPath %>/newstudentregistration"><button>登録画面へ戻る</button></a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.student.Student" %>
<%@ page import="model.student.StudentList" %>

<% String contextPath = request.getContextPath(); %>
<% StudentList studentList = (StudentList)request.getAttribute("studentList");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生徒一覧</title>
</head>
<body>
	<h1>生徒一覧</h1>

	<label for="order-select">並び替え：</label>
	<select id="order-select">
		<option value="no-asc">生徒番号昇順</option>
		<option value="no-desc">生徒番号降順</option>
	</select>
	<table>
		<thead>
			<tr>
				<th colspan="3">生徒情報</th>
			</tr>
			<tr>
				<th>生徒番号</th><th>クラス</th><th>生徒名</th>
			</tr>
		</thead>
		<tbody>
			<%
			for(Student student: studentList.getList()) {
				int no = student.getNo().getNo();
				String className = student.getSchoolClass().getName().getName();
				String name = student.getName().getName();
			%>
			<tr>
				<td><%= no %></td><td><%= className %></td><td><%= name %></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<a href="<%= contextPath %>/index"><button>TOPへ戻る</button></a>
</body>
</html>
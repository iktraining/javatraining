<%@page import="session.RegistrationSession"%>
<%@page import="service.ClassInfoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.student.StudentName" %>
<%@ page import="model.schoolclass.ClassCode" %>
<%@ page import="model.schoolclass.ClassName" %>
<%@ page import="model.schoolclass.SchoolClass" %>
<%@ page import="model.subject.SubjectList" %>
<%@ page import="model.subject.Subject" %>
<%@ page import="model.subject.SubjectCode" %>
<%@ page import="model.subject.SubjectName" %>
<%@ page import="model.record.Record" %>

<% String contextPath = request.getContextPath(); %>

<% RegistrationSession registrationSession = (RegistrationSession)session.getAttribute("registrationSession"); %>


<% StudentName studentName = (StudentName)request.getAttribute("studentName");%>
<% SchoolClass schoolClass = (SchoolClass)request.getAttribute("schoolClass");%>
<% SubjectList subjectList = (SubjectList)request.getAttribute("subjectList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>登録内容確認</h1>
	<form action="./registrationexecution" method=POST>

		<h2>生徒情報入力</h2>
		<ul>
			<li>
				<label for="student-name"><span>※</span>生徒名：</label>
				<input type="text" id="student-name" name="student_name" value="<%= registrationSession.getStudentName().getName() %>" readonly>
			</li>
			<li>
				<label for="class-select"><span>※</span>クラス：</label>
				<input type="text" id="class-select" name="<%= registrationSession.getSchoolClass().getCode().getCode() %>" value="<%= registrationSession.getSchoolClass().getName().getName() %>" readonly>
			</li>
		</ul>

		<h2>成績情報入力</h2>


		<p class="input-attention">※印があるのは必須入力項目です</p>
		<button type="button" onclick="location.href='<%= contextPath %>/newstudentregistration'">内容修正する</button>
		<input type="submit" value="登録">
	</form>
</body>
</html>
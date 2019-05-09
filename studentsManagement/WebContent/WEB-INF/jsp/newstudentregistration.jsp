<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="session.RegistrationSession"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.student.StudentName" %>
<%@ page import="model.schoolclass.SchoolClassList" %>
<%@ page import="model.schoolclass.SchoolClass" %>
<%@ page import="model.schoolclass.ClassCode" %>
<%@ page import="model.schoolclass.ClassName" %>
<%@ page import="model.subject.SubjectList" %>
<%@ page import="model.subject.Subject" %>
<%@ page import="model.subject.SubjectCode" %>
<%@ page import="model.subject.SubjectName" %>
<%@ page import="model.record.Record" %>
<%@ page import="model.validation.RegistrationValidation" %>

<% String contextPath = request.getContextPath(); %>

<% RegistrationSession registrationSession = (RegistrationSession)session.getAttribute("registrationSession"); %>
<% RegistrationValidation registrationValidation = (RegistrationValidation)session.getAttribute("registrationValidation"); %>

<% SchoolClassList schoolClassList = (SchoolClassList)request.getAttribute("schoolClassList");%>
<% SubjectList subjectList = (SubjectList)request.getAttribute("subjectList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
	<h1>新規登録</h1>
	<form action="./newstudentregistration" method="POST">
		<h2>生徒情報入力</h2>
		<ul>
			<li>
				<label for="student-name"><span>※</span>生徒名：</label>

				<%
				String writtenName = "";
				if(registrationSession != null){
					if(registrationSession.hasStudentName()){
						writtenName = registrationSession.getStudentName().getName();
					}
				}
				%>
				<input type="text" id="student-name" name="student_name" value="<%= writtenName %>" required>
				<!-- <input type="text" id="student-name" name="student_name" value="<%= writtenName %>"> -->
				<%
				if(null != registrationValidation){
					if(!registrationValidation.hasStudentName()){
				%>
				<p>生徒名を入力してください</p>
				<%
					}
					if(!registrationValidation.normalStudentNameLength()){
				%>
				<p>生徒名は１文字から６０文字以内で入力してください</p>
				<%
					}
				}
				%>
			</li>
			<li>
				<label for="class-select"><span>※</span>クラス：</label>
				<!-- <select id="class-select" name="class_select" required> -->
				<select id="class-select" name="class_select">
				<option value="">- 選択してください -</option>
				<%
				String selectedClass = "";
				if(registrationSession != null){
					if(registrationSession.hasSchoolClass()){
						selectedClass = registrationSession.getSchoolClass().getCode().getCode();
						out.print(selectedClass);
					}
				}
				for(SchoolClass schoolClass:schoolClassList.getList()) {
				%>
				<option value="<%= schoolClass.getCode().getCode() %>" <% if(selectedClass.equals(schoolClass.getCode().getCode())){out.print("selected");}%>><%= schoolClass.getName().getName() %></option>
				<%
				}
				%>
				</select>
				<%
				if(null != registrationValidation){
					if(!registrationValidation.hasClassCode()){
				%>
				<p>クラスを選択してください</p>
				<%
					}
					if(!registrationValidation.matchClassCode()){
				%>
				<p>正しくクラスを選択してください</p>
				<%
					}
				}
				%>
			</li>
		</ul>
		<h2>成績情報入力</h2>
		<ul>
			<%
			for(Subject subject: subjectList.getList()){
			%>
			<li>
				<label for="<%= subject.getCode().getCode() %>"><%= subject.getName().getName() %>：</label>
				<input type="number" id="<%= subject.getCode().getCode() %>" name="<%= subject.getCode().getCode() %>>" min="1" max="100">
			</li>
			<%
			}
			%>
		</ul>

		<p class="input-attention">※印があるのは必須入力項目です</p>
		<button type="button" onclick="location.href='<%= contextPath %>/index'">TOPへ戻る</button>
		<input type="submit" value="登録">
	</form>
</body>
</html>
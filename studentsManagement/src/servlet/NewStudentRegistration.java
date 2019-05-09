package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataaccess.dbconnection.DBConnection;
import model.record.Record;
import model.record.RecordPoint;
import model.schoolclass.ClassCode;
import model.schoolclass.ClassName;
import model.schoolclass.SchoolClass;
import model.schoolclass.SchoolClassList;
import model.student.StudentName;
import model.subject.Subject;
import model.subject.SubjectList;
import model.validation.RegistrationValidation;
import model.validation.Validation;
import service.ClassInfoService;
import service.SubjectInfoService;
import session.RegistrationSession;

@WebServlet("/newstudentregistration")
public class NewStudentRegistration extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			DBConnection.connect();
			SchoolClassList schoolClassList = new ClassInfoService().getSchoolClassList();
			SubjectList subjectList = new SubjectInfoService().getSubjectList();
			request.setAttribute("schoolClassList", schoolClassList);
			request.setAttribute("subjectList", subjectList);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newstudentregistration.jsp");
			requestDispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.cut();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			//セッション開始
			HttpSession session = request.getSession(true);

			StudentName studentName = new StudentName(request.getParameter("student_name"));
			ClassCode classCode = new ClassCode(request.getParameter("class_select"));

			RegistrationValidation registrationValidation = inputStudentInfoValidation(studentName, classCode);

			SchoolClass schoolClass = null;
			if(registrationValidation.studentInfoEvalution()) {
				try {
					DBConnection.connect();
					ClassInfoService classInfoService = new ClassInfoService();
					Optional<ClassName> optionalClassName = classInfoService.getClassName(classCode);
					ClassName className = new ClassName(optionalClassName.orElseThrow().getName());
					schoolClass = new SchoolClass(classCode, className);
				}catch(NoSuchElementException e) {
					registrationValidation.setMatchClassCodeEvaluation(false);
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					DBConnection.cut();
				}
			}
//成績情報
			SubjectInfoService subjectInfoService = new SubjectInfoService();
			ArrayList<Record> records = new ArrayList<Record>();
			try {
				DBConnection.connect();
				SubjectList subjectList = subjectInfoService.getSubjectList();
				for(Subject subject: subjectList.getList()) {
					String recordPoint = request.getParameter(subject.getCode().getCode());
					if(inputRecordsValidation(recordPoint)) {
						records.add(new Record(subject, new RecordPoint(Integer.parseInt(recordPoint))));
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBConnection.cut();
			}
			//セッションに入れる
			RegistrationSession registrationSession = new RegistrationSession(studentName, schoolClass);
			session.setAttribute("registrationSession", registrationSession);
			session.setAttribute("registrationValidation", registrationValidation);
			//session.setAttribute("records", records);

			RequestDispatcher requestDispatcher = null;
			if(registrationValidation.studentInfoEvalution()) {
				//OK 確認画面へ
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentregistrationconfirmation.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
			//NG 登録画面へ
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/newstudentregistration?=error");
	}

	public static RegistrationValidation inputStudentInfoValidation(StudentName studentName, ClassCode classCode) {
		RegistrationValidation registrationValidation = new RegistrationValidation();
		//名前空欄チェックを行う
		if(studentName.isEmpty()) {
			registrationValidation.setStudentNameExistEvaluation(false);
		}
		//名前文字数チェックを行う
		if(!studentName.lengthValidation()) {
			registrationValidation.setStudentNameLengthEvaluation(false);
		}
		//クラスの選択チェック
		if(classCode.isEmpty()) {
			registrationValidation.setClassCodeExistEvaluation(false);
		}
		if(registrationValidation.hasClassCode()) {
			registrationValidation.setClassCodeExistEvaluation(true);
			//DBにあるクラスかどうかの確認をする
			try {
				DBConnection.connect();
				ClassInfoService classInfoService = new ClassInfoService();
				Optional<ClassName> optionalClassName = classInfoService.getClassName(classCode);
			}catch(NoSuchElementException e) {
				registrationValidation.setMatchClassCodeEvaluation(false);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBConnection.cut();
			}
		}
		return registrationValidation;

	}

	public static boolean inputRecordsValidation(String recordPoint) {
			if(!Validation.numberOfStringType(recordPoint)) {
				return false;
			}
			if(RecordPoint.rangeValidation(Integer.parseInt(recordPoint))){
				return false;
			}
			return true;
	}
}

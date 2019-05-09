package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataaccess.dbconnection.DBConnection;
import model.schoolclass.SchoolClass;
import model.student.StudentName;
import service.StudentInfoService;
import session.RegistrationSession;

@WebServlet("/registrationexecution")
public class RegistrationExecution  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			//セッションから生徒名とクラス情報を取得
			HttpSession session = request.getSession(false);
			RegistrationSession registrationSession = (RegistrationSession)session.getAttribute("registrationSession");
			StudentName studentName = registrationSession.getStudentName();
			SchoolClass schoolClass = registrationSession.getSchoolClass();
			//登録実行
			try {
				DBConnection.connect();
				StudentInfoService studentInfoService = new StudentInfoService();
				studentInfoService.register(studentName, schoolClass);
				//登録成功
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/completeregistration.jsp");
				dispatcher.forward(request, response);
				return;
			}catch(RuntimeException e) {
				//登録失敗
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/failureregistraion.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				DBConnection.cut();
			}
	}
}

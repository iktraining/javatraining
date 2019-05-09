package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.dbconnection.DBConnection;
import service.StudentInfoService;

@WebServlet("/allstudentlist")
public class AllStudentList extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		StudentInfoService studentInfoService = new StudentInfoService();
		try {
			DBConnection.connect();
			request.setAttribute("studentList", studentInfoService.getStudentListOrderByNo());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/allstudentlist.jsp");
			requestDispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.cut();
		}
	}
}

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = -1168770516176088887L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//入力情報のセッションオブジェクト破棄
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute("registrationSession");
			session.removeAttribute("registrationValidation");
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		requestDispatcher.forward(request, response);
	}
}

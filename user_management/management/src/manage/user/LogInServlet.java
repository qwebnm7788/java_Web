package manage.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/login")
public class LogInServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try {
			HttpSession session = request.getSession();
			User.login(userId, password);
			session.setAttribute("userId", userId);
			
			response.sendRedirect("/");
		} catch(UserNotFoundException e) {
			forwardJSP(request, response, "�������� �ʴ� ������Դϴ�. �ٽ� �α��� �ϼ���");
		} catch(PasswordMismatchException e) {
			forwardJSP(request, response, "��й�ȣ�� Ʋ���ϴ�. �ٽ� �α��� �ϼ���");
		}
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
}

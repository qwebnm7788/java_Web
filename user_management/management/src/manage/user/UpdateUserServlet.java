package manage.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LogInServlet.SESSION_USER_ID);
		if(sessionUserId == null) {
			response.sendRedirect("/");
			return;
		}
		
		String userId = request.getParameter("userId");
		if(!sessionUserId.equals(userId)) {
			response.sendRedirect("/");
			return;
		}
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		System.out.println(request.getParameter("userId"));
		System.out.println(request.getParameter("name"));
		
		User user = new User(userId, password, email, name);
		UserDAO	userDAO = new UserDAO();
		try {
			userDAO.updateUser(user);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		response.sendRedirect("/");
	}
}

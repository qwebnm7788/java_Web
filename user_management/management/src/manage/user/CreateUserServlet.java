package manage.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		User user = new User(userId, password, email, name);
		UserDAO	userDAO = new UserDAO();
		try {
			userDAO.addUser(user);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("/");
	}
}

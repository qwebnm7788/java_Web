package manage.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {					//update_form.jsp �̵� �� ���� ������ �������� ����
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = SessionUtils.getStringValue(session, LogInServlet.SESSION_USER_ID);
		if(userId == null) {
			response.sendRedirect("/");
			return;
		}
		
		System.out.println("User id : " + userId);
		
		UserDAO userDao = new UserDAO();
		try {
			User user = userDao.findByUserId(userId);					//user������ ����
			request.setAttribute("isUpdate", true); 						//update�� create�� ������ ���� �Ӽ�
			request.setAttribute("user", user);										//user������ request�� ����
			//forward������� �̵��� request�� ���� ����� �� �ְ� redirect ��Ŀ����� �׷��� �ʴ�.
			RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
		}
	}
}

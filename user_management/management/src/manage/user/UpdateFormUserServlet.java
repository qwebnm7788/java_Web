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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object object = session.getAttribute(LogInServlet.SESSION_USER_ID);
		if(object == null) {				//�α����� ���� �ʰ� �������������� ���� ���
			resp.sendRedirect("/");
			return;
		}
		String userId = (String)object;
		System.out.println("User id : " + userId);
		UserDAO userDao = new UserDAO();
		try {
			User user = userDao.findByUserId(userId);					//user������ ����
			req.setAttribute("user", user);										//user������ request�� ����
			//forward������� �̵��� request�� ���� ����� �� �ְ� redirect ��Ŀ����� �׷��� �ʴ�.
			RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
			rd.forward(req, resp);
		} catch (SQLException e) {
		}
	}
}

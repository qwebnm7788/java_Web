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
public class UpdateFormUserServlet extends HttpServlet {					//update_form.jsp 이동 전 유저 정보를 꺼내오는 서블릿
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
			User user = userDao.findByUserId(userId);					//user정보를 얻어옴
			request.setAttribute("isUpdate", true); 						//update와 create의 구분을 위한 속성
			request.setAttribute("user", user);										//user정보를 request에 저장
			//forward방식으로 이동시 request의 값을 사용할 수 있고 redirect 방식에서는 그렇지 않다.
			RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
		}
	}
}

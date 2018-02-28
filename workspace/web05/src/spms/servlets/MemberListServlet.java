package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;


@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			//jsp에 값을 전달하기 위해 request에 저장한다.
			request.setAttribute("members", memberDao.selectList());
			
			//디스패처를 이용하여 JSP에 위임
			RequestDispatcher rd = request.getRequestDispatcher("/Member/MemberList.jsp");
			rd.include(request, response);				//request, response 변수를 공유하도록 한다.
		} catch (Exception e) {
			//에러 페이지 출력
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error/Error.jsp");
			rd.forward(request, response);
		}
	}
}

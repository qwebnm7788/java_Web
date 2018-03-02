<%@page import="manage.user.PasswordMismatchException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="manage.user.UserNotFoundException"%>
<%@page import="javax.servlet.RequestDispatcher" %>
<%@ page import="manage.db.Database" %>
<%@ page import="manage.user.User" %>

<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	
	try {
		User.login(userId, password);
		session.setAttribute("userId", userId);
		
		response.sendRedirect("/");
	} catch(UserNotFoundException e) {
		request.setAttribute("errorMessage", "�������� �ʴ� ������Դϴ�. �ٽ� �α��� �ϼ���");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	} catch(PasswordMismatchException e) {
		request.setAttribute("errorMessage", "��й�ȣ�� Ʋ���ϴ�. �ٽ� �α��� �ϼ���");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
%>
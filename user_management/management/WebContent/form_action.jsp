<%@page import="manage.db.Database"%>
<%@page import="manage.user.User" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String userId = request.getParameter("userId");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	
	User user = new User(userId, email, password, name);
	Database.addUser(user);
	
	response.sendRedirect("/");
%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/commons/_head.jspf" %>
	    <link rel="stylesheet" href="/stylesheets/custom/login.css">
	    <title>title goes here</title>
	</head>
	<body>
		<div class="container">
			<form class="form-signin" action="/users/login" method="post">
		        <img class="mb-4" src="images/bootstrap-solid.svg" alt="" width="72" height="72">
		        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		        <c:if test="${not empty errorMessage }">
			        <div class="alert alert-danger">
			        	<p class="small">${errorMessage }</p>
			        </div>
		        </c:if>
		        <div class="control-group">
			        <input type="text" class="form-control mb-2" placeholder="User ID" required autofocus name="userId">
		        </div>
		        <div class="control-group">
			        <input type="password" class="form-control mb-3" placeholder="Password" name="password">
		        </div>
		        <div class="control-group mb-3">
		            <label><input type="checkbox"> Remember me</label>
		        </div>
		        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		        <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
	    	</form>
    	</div>
	</body>
</html>
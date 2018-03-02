<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="stylesheets/bootstrap/bootstrap.min.css">
	    <link rel="stylesheet" href="stylesheets/custom/login.css">
	    <title>title goes here</title>
	</head>
	<body>
		<div class="container">
			<form class="form-signin" action="/login_action.jsp" method="post">
		        <img class="mb-4" src="images/bootstrap-solid.svg" alt="" width="72" height="72">
		        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		        <%
		        	Object errorMessage = request.getAttribute("errorMessage");
		        	if(errorMessage != null) {
		        %>
		        <div class="alert alert-danger">
		        	<p class="small"><%= errorMessage %></p>
		        </div>
		        <%
		        	}
		        %>
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
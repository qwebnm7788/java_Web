<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/commons/_head.jspf" %>
	    <link rel="stylesheet" href="/stylesheets/custom/form.css">
	    <title>title goes here</title>
	</head>
	<body>
		<%@ include file="/commons/_top.jspf" %>
		
	    <div role="main" class="container">
	        <div class="row">
	            <div class="col-md-8 mx-auto form-group">
	                <form action="/users/save" method="post">
	                    <h2>Registration</h2>
	                    <div>
	                    	<label for="">User ID</label>
	                    	<input class="form-control" type="text" placeholder="User Id" autofocus name="userId">
	                    </div>
	                    <div>
	                        <label for="">Full Name</label>
	                        <div>
	                            <input class="form-control" type="text" placeholder="Full Name" name="name">
	                        </div>
	                    </div>
	                    <div>
	                        <label for="">Email</label>
	                        <div>
	                            <input class="form-control" type="text" placeholder="Email" name="email">
	                        </div>
	                    </div>
	                    <div>
	                        <label for="">Password</label>
	                        <div>
	                            <input class="form-control" type="password" placeholder="Password" name="password">
	                        </div>
	                    </div>
	                    <button class="mt-4 btn btn-primary" type="submit">Register</button>
	                </form>
	            </div>
	        </div>
	    </div>
	</body>
</html>
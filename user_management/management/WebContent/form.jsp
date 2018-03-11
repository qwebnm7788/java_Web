<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/_head.jspf"%>
<link rel="stylesheet" href="/stylesheets/custom/form.css">
<title>title goes here</title>
</head>
<body>
	<%@ include file="/commons/_top.jspf"%>

	<div role="main" class="container">
		<div class="row">
			<div class="col-md-8 mx-auto form-group">

				<c:set var="actionUrl" value="/users/create" />
				<c:if test="${not empty user.userId }">
					<c:set var="actionUrl" value="/users/update" />
				</c:if>

				<form action="${actionUrl}" method="post">
					<c:choose>
						<c:when test="${empty user.userId }">
							<h2>회원가입</h2>
						</c:when>
						<c:otherwise>
							<h2>개인정보수정</h2>
						</c:otherwise>
					</c:choose>
					<div>
						<label for="">User ID</label>
						<c:choose>
							<c:when test="${empty user.userId }">
								<input class="form-control" type="text" placeholder="User Id"
									name="userId" value="${user.userId }" autofocus>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="userId" value="${user.userId }">
								<input class="form-control" type="text" placeholder="User Id"
									name="userId" value="${user.userId }" disabled>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						<label for="">Full Name</label>
						<div>
							<input class="form-control" type="text" placeholder="Full Name"
								name="name" value="${user.name }">
						</div>
					</div>
					<div>
						<label for="">Email</label>
						<div>
							<input class="form-control" type="text" placeholder="Email"
								name="email" value="${user.email }">
						</div>
					</div>
					<div>
						<label for="">Password</label>
						<div>
							<input class="form-control" type="password"
								placeholder="Password" name="password" value="${user.password }">
						</div>
					</div>

					<c:set var="buttonMsg" value="등록" />
					<c:if test="${not empty user.userId }">
						<c:set var="buttonMsg" value="수정" />
					</c:if>
					<button class="mt-4 btn btn-primary" type="submit">${buttonMsg}</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
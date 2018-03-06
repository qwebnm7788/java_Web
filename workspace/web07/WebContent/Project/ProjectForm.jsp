<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>프로젝트 등록</title>
	</head>
	<body>
		<h1>프로젝트 등록</h1>
		<form action="add.do" method="post">
			<label for="title">제목 </label>
			<input type="text" name="title"><br>
			<label for="content">내용 </label>
			<textarea rows="5" cols="40" name="content"></textarea><br>
			<label for="sta_date">시작일 </label>
			<input type="text" name="startDate" placeholder="예)2018-01-01"><br>
			<label for="end_date">종료일 </label>
			<input type="text" name="endDate" placeholder="예)2018-01-01"><br>
			<label>태그</label>
			<input type="text" name="tags" placeholder="예)태그1 태그2 태그3"><br><br>
			<input type="submit" value="추가">
			<input type="reset" value="취소">
		</form>
	</body>
</html>
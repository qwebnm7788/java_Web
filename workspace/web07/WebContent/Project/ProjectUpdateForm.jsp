<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>프로젝트 정보</title>
	</head>
	<body>
		<h1>프로젝트 정보</h1>
		<form action="update.do" method="post">
			번호 <input type="text" name="no" value="${project.no }" readonly><br>
			제목 <input type="text" name="title" value="${project.title }"><br>
			내용 <textarea rows="5" cols="30" name="content">${project.content }</textarea><br>
			시작일 <input type="text" name="startDate" value="${project.startDate }"><br>
			종료일<input type="text" name="endDate" value="${project.endDate }"><br>
			상태
			<select>
				<option value="0" ${project.state == 0 ? "selected" : "" }>준비</option>
				<option value="1" ${project.state == 1 ? "selected" : "" }>진행</option>
				<option value="2" ${project.state == 2 ? "selected" : "" }>완료</option>
				<option value="3" ${project.state == 3 ? "selected" : "" }>취소</option>
			</select>
			<br>
			태그<input type="text" name="tags" value="${project.tags }"><br><br>
			
			<input type="submit" value="저장">
			<input type="button" value="삭제" onclick='location.href="delete.do?no=${request.no}"'>
			<input type="button" value="취소" onclick='location.href="list.do"'>
		</form>
	</body>
</html>
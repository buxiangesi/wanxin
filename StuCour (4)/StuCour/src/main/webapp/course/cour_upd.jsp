<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CourUpd</title>
</head>
<body>
	<form action="${request.contextPath}/StuCour/CourUpdServlet" method="post">
		<input type="hidden" name="id" value=${ requestScope.cour.id }><br />
		name: <input type="text" name="name" value=${ requestScope.cour.name }><br />
		teacher: <input type="text" name="teacher" value=${ requestScope.cour.teacher }><br />
		<input type="submit">
		<input type="reset">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>MajorAdd</title>
</head>
<body>
	<form action="${request.contextPath}/StuCour/MajorAddServlet" method="post">
		id: <input type="text" name="id"><br />
		name: <input type="text" name="name"><br />
		<input type="submit">
		<input type="reset">
	</form>
</body>
</html>
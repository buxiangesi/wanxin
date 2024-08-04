<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p><a href="${request.contextPath}/StuCour/StuListServlet" target="view">学生信息维护</a></p>
	<p><a href="${request.contextPath}/StuCour/CourListServlet" target="view">课程信息维护</a></p>
	<p><a href="${request.contextPath}/StuCour/MajorListServlet" target="view">专业信息维护</a></p>
</body>
</html>
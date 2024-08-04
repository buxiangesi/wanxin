<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>StuAdd</title>
</head>
<body>
	<form action="${request.contextPath}/StuCour/StuAddServlet" method="post">
		学号: <input type="text" name="id"><br />
		姓名: <input type="text" name="name"><br />
		性别:<br />
		<input type="radio" name="sex" value="男">男<br />
		<input type="radio" name="sex" value="女">女<br />
		年龄: <input type="text" name="age"><br />
		专业:<select name="majorId">
			<option value=""> </option>
			<c:forEach items="${ requestScope.majors }" var="major">
				<option value="${ major.id }">${ major.name }</option>
			</c:forEach>
		</select><br />
		<input type="submit">
		<input type="reset">
	</form>
</body>
</html>
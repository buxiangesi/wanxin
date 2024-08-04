<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>StuUpd</title>
</head>
<body>
	<form action="${request.contextPath}/StuCour/StuUpdServlet" method="post">
		<input type="hidden" name="id" value=${ requestScope.stu.id }><br />
		姓名: <input type="text" name="name" value=${ requestScope.stu.name }><br />
		性别:<br />
		<input type="radio" name="sex" value="男" <c:if test="${ requestScope.stu.sex eq '男' }">checked</c:if> />男<br />
		<input type="radio" name="sex" value="女" <c:if test="${ requestScope.stu.sex eq '女' }">checked</c:if> />女<br />
		年龄: <input type="text" name="age" value=${ requestScope.stu.age }><br />
		专业:<select name="majorId">
			<c:forEach items="${ requestScope.majors }" var="major">
				<option value="${ major.id }" <c:if test="${ major.id eq requestScope.stu.major.id }">selected</c:if> >${ major.name }</option>
			</c:forEach>
		</select><br />
		<input type="submit">
		<input type="reset">
	</form>
</body>
</html>
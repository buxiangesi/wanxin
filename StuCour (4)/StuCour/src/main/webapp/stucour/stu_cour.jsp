<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生选课</title>
</head>
<body>
	<form action="" method="post">
		<input type="hidden" name="stuId" value="${ requestScope.stu.id }">
		<table border='1'>
			<tr>
				<th></th><th>课程号</th><th>课程名</th><th>主讲教师</th>
			</tr>			
			<c:forEach items="${requestScope.cours}" var="cour">
				<tr>
					<td><input type="checkbox" name="selCours" value="${ cour.id }" <c:if test="${ cour.selected eq '1' }"> checked </c:if> /></td>
					<td>${ cour.id }</td>
					<td>${ cour.name }</td>
					<td>${ cour.teacher }</td>
				</tr>
			</c:forEach>		
		</table>
		<input type="submit" value="选课">
	</form>
</body>
</html>
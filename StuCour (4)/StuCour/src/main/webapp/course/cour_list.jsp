<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ request.contextPath }/StuCour/CourListServlet" method="post">
		id:<input type="text" name="id" <c:if test="${ requestScope.cour.id ne -1}"> value="${ requestScope.cour.id }"</c:if> /><br />
		name:<input type="text" name="name" value="${ requestScope.cour.name }" /><br />		
		teacher:<input type="text" name="teacher" value="${ requestScope.cour.teacher }" /><br />
		<input type="submit" value="serach"/><input type="reset" />
	</form>
	<hr />
	<table border='1'>
		<tr>
			<th>No.</th><th>name</th><th>teacher</th><th>operation</th>
		</tr>	
		
		<c:forEach items="${requestScope.cours}" var="cour">
			<tr>
				<td>${ cour.id }</td>
				<td>${ cour.name }</td>
				<td>${ cour.teacher }</td>
				<td>
					<a href="${request.contextPath}/StuCour/CourUpdServlet?id=${cour.id}">update</a>
					<a href="${request.contextPath}/StuCour/CourDelServlet?id=${cour.id}">delete</a>
				</td>
			</tr>
		</c:forEach>		
	</table>
	<a href="${request.contextPath}/StuCour/CourAddServlet">insert course</a>
</body>
</html>
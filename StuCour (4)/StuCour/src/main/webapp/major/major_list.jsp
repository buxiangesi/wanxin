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
	<form action="${ request.contextPath }/StuCour/MajorListServlet" method="post">
		id:<input type="text" name="id" <c:if test="${ requestScope.major.id ne -1}"> value="${ requestScope.major.id }"</c:if> /><br />
		name:<input type="text" name="name" value="${ requestScope.major.name }" /><br />	
		<input type="submit" value="serach"/><input type="reset" />
	</form>
	<hr />
	<table border='1'>
		<tr>
			<th>No.</th><th>name</th><th>operation</th>
		</tr>	
		
		<c:forEach items="${requestScope.majors}" var="major">
			<tr>
				<td>${ major.id }</td>
				<td>${ major.name }</td>
				<td>
					<a href="${request.contextPath}/StuCour/MajorUpdServlet?id=${major.id}">update</a>
					<a href="${request.contextPath}/StuCour/MajorDelServlet?id=${major.id}">delete</a>
				</td>
			</tr>
		</c:forEach>		
	</table>
	<a href="${request.contextPath}/StuCour/MajorAddServlet">insert course</a>
</body>
</html>
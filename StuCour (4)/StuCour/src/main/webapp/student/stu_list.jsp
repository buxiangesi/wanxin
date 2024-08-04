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
	<form action="${ request.contextPath }/StuCour/StuListServlet" method="post">
		学号:<input type="text" name="id" <c:if test="${ requestScope.stu.id ne -1}"> value="${ requestScope.stu.id }"</c:if> /><br />
		姓名:<input type="text" name="name" value="${ requestScope.stu.name }"><br />
		性别:<select name=sex>
			<option value="" selected></option>
			<option value="男" <c:if test="${ requestScope.stu.sex eq '男'}">selected</c:if> >男</option>
			<option value="女" <c:if test="${ requestScope.stu.sex eq '女'}">selected </c:if> >女</option>
		</select>
		年龄:<input type="text" name="age" <c:if test="${ requestScope.stu.age ne -1}"> value="${ requestScope.stu.age }"</c:if>"><br />
		专业:<select name="majorId">
			<option value=""> </option>
			<c:forEach items="${ requestScope.majors }" var="major">
				<option value="${ major.id }" <c:if test="${ major.id eq requestScope.stu.major.id}">selected</c:if> >${ major.name }</option>
			</c:forEach>
		</select><br />
		<input type="submit" value="搜索"/><input type="reset" />
	</form>
	<hr />
	<table border='1'>
		<tr>
			<th>学号</th><th>姓名</th><th>性别</th><th>年龄</th><th>专业</th><th>operation</th>
		</tr>	
		
		<c:forEach items="${requestScope.stus}" var="stu">
			<tr>
				<td>${ stu.id }</td>
				<td>${ stu.name }</td>
				<td>${ stu.sex }</td>
				<td>${ stu.age }</td>
				<td>${ stu.major.name }</td>
				<td>
					<a href="${request.contextPath}/StuCour/StuUpdServlet?id=${stu.id}">编辑</a>
					<a href="${request.contextPath}/StuCour/StuDelServlet?id=${stu.id}">删除</a>
					<a href="${request.contextPath}/StuCour/StuCourServlet?id=${stu.id}">选课</a>
				</td>
			</tr>
		</c:forEach>		
	</table>
	<a href="${request.contextPath}/StuCour/StuAddServlet">insert student</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/components/include.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users - Tyrion</title>
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<h1 class="left">Persons</h1>
	<p class="left" style="position: relative; top: 10px;">  </a></p>
	<br class="clear"/>
	<table style="font-size: 12px;">
		<tr>
			<th>Id</th>
			<th>Username</th>
			<th>Email</th>
			<th>Password</th>
			<th></th>
		</tr>
		<c:forEach var="t" items="${persons }">
			<tr>
				<td>${t.id}</td>
				<td>${t.username}</td>
				<td>${t.email}</td>
				<td>${t.password}</td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>
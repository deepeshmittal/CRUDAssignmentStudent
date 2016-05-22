<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
   	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>

<h1 style="text-align:center">Student Page</h1>
<div class="col-md-4" style="border:solid; margin-top:100px;">

	<h3>View Student Grade:</h3>
	<form method="post" action="${pageContext.request.contextPath}/submit">
	Task: <input type="text" name="task" value="View Grade" readonly><br>
	Student ID: <input type="text" name="studentID" required> (required)<br>
	Assignment: <input type="text" name="assignID"> (optional)<br>
	<input type="submit" value="Submit">
	</form>

</div>
<div class="col-md-2"></div>
<div class="col-md-4" id="ouput" style="margin-top:100px; border:solid">
<h3>Response:</h3> <p>${message}</p>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
	String[] imageUrlVec = (String[])request.getAttribute("imageUrlVec");
%>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<body>
	<h2>Results Page</h2>
	<% for (int i = 0; i < imageUrlVec.length; ++i) { %>
	<img src="<%=imageUrlVec[i] %>" height="100" width="100">
	<% } %>
</body>
</html>
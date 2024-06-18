<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Utenti</title>
</head>

<body>

<h1>Gestione degli Utenti</h1>

<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>Email</th>
        <th>Password</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Telefono</th>
        <th>isAdmin</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.nome}</td>
            <td>${user.cognome}</td>
            <td>${user.telefono}</td>
            <td>${user.isAdmin}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="<%=request.getContextPath()%>/admin/homepage.jsp">Torna alla Homepage Admin</a>
</body>
</html>

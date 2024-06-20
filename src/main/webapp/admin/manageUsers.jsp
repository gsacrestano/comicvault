<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">

    <title>Gestione Utenti</title>
</head>

<body>
<jsp:include page="/jsp/header.jsp"/>

<h1>Gestione degli Utenti</h1>

<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>Email</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Telefono</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.nome}</td>
            <td>${user.cognome}</td>
            <td>${user.telefono}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a class="aManage" href="<%=request.getContextPath()%>/admin/homepage.jsp">Home</a>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>

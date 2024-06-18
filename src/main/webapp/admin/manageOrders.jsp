<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Gestione Ordini</title>
</head>

<body>
<jsp:include page="/jsp/header.jsp"/>

<h1>Gestione degli Ordini</h1>

<table>
    <thead>
        <tr>
            <th>id</th>
            <th>Email</th>
            <th>Indirizzo</th>
            <th>Data</th>
            <th>Totale</th>
            <th>Dettaglio</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.emailUtente}</td>
                <td>${order.indirizzo}</td>
                <td>${order.data}</td>
                <td>${order.totale}</td>
                <td>Pulsante dettaglio</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<br>
<a href="<%=request.getContextPath()%>/admin/homepage.jsp">Home</a>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>

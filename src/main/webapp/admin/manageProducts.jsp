<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style.css">
    <title>Gestione Prodotti</title>
</head>

<body>
<jsp:include page="/jsp/header.jsp"/>

<h1>Gestione dei Prodotti</h1>

<!--Collegamento per aggiungere un nuovo prodotto -->
<button style="display: inline" class="btn" onclick="location.href='${pageContext.request.contextPath}/admin/addProduct.jsp'">Aggiungi prodotto</button>


<!-- Tabella per visualizzare, aggiornare e cancellare prodotti esistenti -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Prezzo</th>
        <th>Descrizione</th>
        <th>ISBN</th>
        <th>Quantità</th>
        <th>Immagine</th>
        <th colspan="3">Azione</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.nome}</td>
            <td>${product.prezzo}</td>
            <td>${product.descrizione}</td>
            <td>${product.isbn}</td>
            <td>${product.quantita}</td>
            <td>${product.image_path}</td>
            <td> <button type="submit">Aggiorna</button> </td>
            <td> <button type="submit">Modifica</button> </td>
            <td> <button type="submit">Cancella</button> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="<%=request.getContextPath()%>/admin/homepage.jsp">Home</a>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>


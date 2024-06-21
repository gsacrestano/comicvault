<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dettagli Ordine</title>
    <!-- Includi i tuoi stili CSS e script JavaScript qui -->
</head>
<body>
<h2>Dettagli Ordine</h2>

<table>
    <tr>
        <th>ID Ordine:</th>
        <td>${order.id}</td>
    </tr>
    <tr>
        <th>Nome Utente:</th>
        <td>${order.nomeUtente} ${order.cognomeUtente}</td>
    </tr>
    <tr>
        <th>Email Utente:</th>
        <td>${order.emailUtente}</td>
    </tr>
    <tr>
        <th>Data Ordine:</th>
        <td>${order.data}</td>
    </tr>
    <tr>
        <th>Indirizzo:</th>
        <td>${order.indirizzo}</td>
    </tr>
    <tr>
        <th>Totale:</th>
        <td>${order.totale}</td>
    </tr>
</table>

<h3>Prodotti dell'Ordine:</h3>
<table border="1">
    <thead>
    <tr>
        <th>ID Prodotto</th>
        <th>Nome Prodotto</th>
        <th>Prezzo Unitario</th>
        <th>Quantità</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${order.prodotti}" var="prodotto">
        <tr>
            <td>${prodotto.id}</td>
            <td>${prodotto.nome}</td>
            <td>${prodotto.prezzo}</td>
            <td>${prodotto.quantita}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="${pageContext.request.contextPath}/admin/ManageOrdersServlet">Torna alla lista degli ordini</a>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../css/style.css">
    <meta charset="UTF-8">
    <title>Lista Ordini</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>

<h2>Lista Ordini</h2>
<div style="overflow-x: auto">
<table border="1">
    <thead>
    <tr>
        <th>ID Ordine</th>
        <th>Email</th>
        <th>Data</th>
        <th>Totale</th>
        <th>Dettagli</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.emailUtente}</td>
            <td>${order.data}</td>
            <td>${order.totale}</td>
            <td><a style="color: #0d0d0d;" onclick="mostraDettagli(${order.id});">Dettagli</a></td>
        </tr>
        <tr id="dettagli-${order.id}" style="display: none;">
            <td colspan="5">
                <h3>Dettagli Ordine ${order.id}</h3>
                <p><strong>Nome Utente:</strong> ${order.nomeUtente} ${order.cognomeUtente}</p>
                <p><strong>Email Utente:</strong> ${order.emailUtente}</p>
                <p><strong>Indirizzo:</strong> ${order.indirizzo}</p>
                <p><strong>Data:</strong> ${order.data}</p>
                <p><strong>Totale:</strong> ${order.totale}</p>
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
                <hr>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</div>

<br>
<a style="color: #0d0d0d" href="${pageContext.request.contextPath}/admin/homepage.jsp">Torna alla Home Admin</a>
<script src="scripts/manageOrderScript.js"></script>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>

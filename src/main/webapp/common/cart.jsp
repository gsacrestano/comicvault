<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Carrello</title>
</head>
<body>
<h1>Carrello</h1>

<table border="1">
    <tr>
        <th>Prodotto</th>
        <th>Prezzo</th>
        <th>Quantità</th>
        <th>Azioni</th>
    </tr>
    <c:forEach var="prodottoCarrello" items="${carrello.prodotti}">
        <tr>
            <td>${prodottoCarrello.prodotto.nome}</td>
            <td>${prodottoCarrello.prezzo}</td>
            <td>${prodottoCarrello.quantita}</td>
            <td>
                <form action="rimuoviDalCarrello" method="post">
                    <input type="hidden" name="idProdotto" value="${prodottoCarrello.id}">
                    <input type="submit" value="Rimuovi dal Carrello">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

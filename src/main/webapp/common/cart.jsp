<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Carrello</title>
</head>
<body>

<jsp:include page="../jsp/header.jsp"/>

<div class="container">
    <h1>Il tuo carrello</h1>

    <c:if test="${not empty prodottiCarrello}">
        <table>
            <thead>
            <tr>
                <th>Prodotto</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Azioni</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${prodottiCarrello}" var="prodottoCarrello" varStatus="status">
                <tr>
                    <td>${items[status.index].nome}</td> <!-- Nome del prodotto -->
                    <td>${items[status.index].prezzo}&euro;</td> <!-- Prezzo del prodotto -->
                    <!-- <td>${prodottoCarrello.quantita}</td> -->
                    <td>
                        <form action="${pageContext.request.contextPath}/common/UpdateCartItemServlet" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="productId" value="${prodottoCarrello.idProdotto}">
                            <input type="number" name="quantity" min="1" value="${prodottoCarrello.quantita}">
                            <button class="btn" type="submit">Modifica</button>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/common/RemoveCartItemServlet" method="post">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="productId" value="${prodottoCarrello.idProdotto}">
                            <button class="btn" type="submit">Rimuovi</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty prodottiCarrello}">
        <p>Il tuo carrello è vuoto.</p>
    </c:if>

</div>

<jsp:include page="../jsp/footer.jsp"/>

</body>
</html>

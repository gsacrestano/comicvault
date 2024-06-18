<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/errors/404.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Catalogo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<p><img  style="width: 100%" src="images/banner.webp" alt="Banner"></p>


<h1>PRODOTTI</h1>
<form action="${pageContext.request.contextPath}/common/CatalogServlet" method="GET">
    <div id="price-range">
        <input type="range" min="0" max="100" value="${maxPrice}" class="slider" id="priceSlider" name="maxPrice">
        <div id="price-output">$0 - $50</div>
    </div>
    <button id="catologButton" type="submit">Filtra</button>
</form>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const priceSlider = document.getElementById('priceSlider');
        const priceOutput = document.getElementById('price-output');

        // Funzione per aggiornare il testo del prezzo
        function updatePriceOutput() {
            const value = priceSlider.value;
            priceOutput.innerHTML =  "$0-"+"$"+value;
        }

        // Aggiornare il testo del prezzo quando l'utente interagisce con lo slider
        priceSlider.addEventListener('input', updatePriceOutput);

        // Impostare il testo iniziale del prezzo
        updatePriceOutput();
    });
</script>

<div class="viewer">
    <c:forEach var="product" items="${products}">
        <div class="card">
            <p> <img src="${pageContext.request.contextPath}/images/products/${product.image_path}"></p>
            <p>${product.nome}</p>
            <a href="${pageContext.request.contextPath}/common/ProductDetailsServlet?id=${product.id}"> <button>Dettaglio</button></a>
        </div>
    </c:forEach>
</div>

<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
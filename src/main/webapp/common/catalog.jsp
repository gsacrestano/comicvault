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

<!-- Aggiungi il pulsante per l'aggiunta di un prodotto se l'utente è un amministratore -->
<c:if test="${isAdmin == 1}">
    <form action="${pageContext.request.contextPath}/admin/addProduct.jsp" method="get">
        <button type="submit">Aggiungi Prodotto</button>
    </form>
</c:if>

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
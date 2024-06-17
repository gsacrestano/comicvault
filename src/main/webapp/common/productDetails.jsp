<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Prodotto X</title>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>

<h1>Dettagli Prodotto</h1>

<c:if test="${not empty product}">
    <p>ID: ${product.id}</p>
    <p>Nome: ${product.nome}</p>
    <p>Descrizione: ${product.descrizione}</p>
    <p>ISBN: ${product.isbn}</p>
    <p>Prezzo: ${product.prezzo}</p>
    <p>Quantità: ${product.quantita}</p>
    <p>Immagine: ${product.image_path}</p>
</c:if>

<a href="${pageContext.request.contextPath}/index.jsp">Torna alla lista prodotti</a>

<div class="productView">
    <h1 style="display: inline">Titolo</h1>
    <img src="${pageContext.request.contextPath}/images/products/${product.image_path}"></p>

</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
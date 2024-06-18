<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>${product.nome}</title>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>

<h1>${product.nome}</h1>

<div class="productView" >
    <img style="width:45%; display: inline; margin:0 2.5% " src="${pageContext.request.contextPath}/images/products/${product.image_path}">
    <div style="width: 45%; display: inline;">
        <c:if test="${not empty product}">
            <h1>Nome: ${product.nome}</h1>
            <h3>Prezzo: ${product.prezzo}</h3>
            <p>Descrizione: ${product.descrizione}</p>
            <p>ISBN: ${product.isbn}</p>
            <p>Quantità: ${product.quantita}</p>
        </c:if>
    </div>

</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
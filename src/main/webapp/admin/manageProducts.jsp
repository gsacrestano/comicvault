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

<button class="btn" style="width: 30%; font-size: 80%" onclick="location.href='${pageContext.request.contextPath}/admin/addProduct.jsp'">Nuovo Prodotto</button>


<div style="overflow-x: auto">
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
            <td><img src="${pageContext.request.contextPath}/images/products/${product.image_path}" width="50" height="50"> (${product.image_path})</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/updateProduct.jsp" method="post">
                    <input type="hidden" name="id" value="${product.id}" />
                    <input type="hidden" name="nome" value="${product.nome}" />
                    <input type="hidden" name="descrizione" value="${product.descrizione}" />
                    <input type="hidden" name="isbn" value="${product.isbn}" />
                    <input type="hidden" name="prezzo" value="${product.prezzo}" />
                    <input type="hidden" name="quantita" value="${product.quantita}" />
                    <input type="hidden" name="image_path" value="${product.image_path}" />
                    <button class="btn" type="submit">Modifica</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/DeleteProductServlet" method="post" onsubmit="return confirm('Sei sicuro di voler cancellare questo prodotto?');">
                    <input type="hidden" name="id" value="${product.id}" />
                    <button class="btn" type="submit">Cancella</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</div>

<br>
<a class="aManage" href="<%=request.getContextPath()%>/admin/homepage.jsp">Home</a>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>


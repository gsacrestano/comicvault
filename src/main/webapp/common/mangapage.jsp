<%--
  Created by IntelliJ IDEA.
  User: giulio
  Date: 18/06/24
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manga</title>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<div style="width: 29%; display: inline; margin:0 1% 0 0">
    <h3> Filtra in base al prezzo</h3>
    <form action="/submit-form" method="post">
        <label for="5">
            <input type="checkbox" id="5" name="under5" value="5">
           5$
        </label><br>
        <label for="10">
            <input type="checkbox" id="10" name="under10" value="10">
 10$
        </label><br>
        <label for="20">
            <input type="checkbox" id="20" name="under20" value="20">
       20$
        </label><br>
        <label for="50">
            <input type="checkbox" id="50" name="under50" value="50">
            50$
        </label><br>
        <label for="100">
            <input type="checkbox" id="100" name="under100" value="100">
          100$
        </label><br>
        <input type="submit" value="Cerca">
    </form>
</div>
<h1>Novità</h1>
<div class="viewer">
    <c:forEach var="product" items="${latestProducts}">
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

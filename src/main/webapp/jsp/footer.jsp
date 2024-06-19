<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleError.css">
    <title>Footer</title>
</head>
<body>
<footer>
    <div class="footer-container">
        <div class="footer-logo">
            <img src="${pageContext.request.contextPath}/images/logo.jpeg" alt="Logo">
            <h2 style="display: inline">Comic Vault</h2>
        </div>
        <ul class="footer-nav">
            <li><a href="${pageContext.request.contextPath}/common/CatalogServlet">Prodotti</a></li>
            <li><a href="https://github.com/gsacrestano/comicvault.git">Github</a></li>
            <li><a href="${pageContext.request.contextPath}/common/info.jsp">Chi siamo</a></li>
        </ul>

    </div>
    <p class="footer-copyright">&#169; ComicVault</p>

</footer>
</body>
</html>

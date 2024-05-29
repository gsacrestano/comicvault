<%--
  Created by IntelliJ IDEA.
  User: giulio
  Date: 28/05/24
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="productView">
    <h1 style="display: inline">Titolo</h1>
<img src="${pageContext.request.contextPath}/Images/copertina.webp">

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>

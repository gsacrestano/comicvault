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
<h1>Titolo</h1>
<div class="productView" style=" display:flex; width: 80%">
<img style="width:50%; display: inline" src="Images/copertina.webp"/>
<div style="width: 50%; display: inline;">
    <p></p>
</div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>

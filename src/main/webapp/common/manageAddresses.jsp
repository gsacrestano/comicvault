<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Indirizzi</title>
</head>

<body>

<jsp:include page="../jsp/header.jsp"/>

<h1>Indirizzi</h1>

<!--Collegamento per aggiungere un nuovo indirizzo -->

<button class="btn" onclick="location.href='${pageContext.request.contextPath}/common/addAddress.jsp'">Nuovo Indirizzo</button>

<div class="viewer">
    <c:forEach var="address" items="${addresses}">
        <div class="card">
            <p>Via: ${address.via}</p>
            <p>Città: ${address.citta}</p>
            <p>Provincia: ${address.provincia}</p>
            <p>Cap: ${address.cap}</p>
            <p>Nazione: ${address.nazione}</p>
        </div>
    </c:forEach>
</div>

<jsp:include page="../jsp/footer.jsp"/>

</body>
</html>
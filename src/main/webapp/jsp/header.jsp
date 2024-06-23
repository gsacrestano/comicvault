<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <base href="<%=request.getContextPath()%>/">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <title>Navbar con Logo</title>
</head>
<body>

<div class="topnav">
    <a href="index.jsp">
        <img src="images/logo.jpeg" alt="Logo">
    </a>

    <a href="${pageContext.request.contextPath}/index.jsp">Comic Vault</a>

    <div class="search-container">
        <form action="">
            <label>
                <input id="search" type="text" placeholder="Cerca.." name="name" oninput="sendSliderValue()">
            </label>
            <span class="fa fa-search" style="color: white; margin-right: 10px" id ="num"> 0 </span>
        </form>
    </div>
<script>
    function sendSliderValue() {
        const xhr = new XMLHttpRequest();
        const timestamp = new Date().getTime(); // Aggiungi un timestamp per evitare il caching
        const searchValue = document.getElementById('search').value; // Assumi che ci sia un input con id="search"
        const str = `${pageContext.request.contextPath}/common/NumProductServlet?name=` + searchValue;
        xhr.open('GET', str, true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('num').innerHTML = xhr.responseText;
            }
        };

        xhr.send();
    }

</script>
    <div class="links">

        <!-- Scelta pulsante a seconda se loggato (e tipo di permessi)-->
        <c:choose>
            <c:when test="${isAdmin == 1}"> <!-- Accesso effettuato come admin -->
                <a href="${pageContext.request.contextPath}/admin/homepage.jsp">Profilo</a>
            </c:when>
            <c:when test="${isAdmin == 0}"> <!-- Accesso effettuato come user -->
                <a href="${pageContext.request.contextPath}/common/homepage.jsp">Profilo</a>

            </c:when>
            <c:otherwise> <!-- Non ancora effettuato l'accesso -->
                <a href="${pageContext.request.contextPath}/common/login.jsp">Accedi</a>
            </c:otherwise>
        </c:choose>

        <a href="${pageContext.request.contextPath}/common/RetrieveAccountCartServlet">Carrello</a>
    </div>
</div>

</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="../css/style.css">
    <meta charset="UTF-8">
    <title>Nuovo Indirizzo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>

<div id="updateProduct">
<form id="addAddressForm" action="${pageContext.request.contextPath}/common/AddAddressServlet" method="post">
    <h1 style="color: #0d0d0d">Aggiungi Indirizzo</h1>
    <label id="addAddressLabel" for="via">Via:</label>
    <input class="addAddressInput" type="text" id="via" name="via" required>

    <label for="citta">Citta:</label>
    <input class="addAddressInput" type="text" id="citta" name="citta" required>

    <label for="provincia">Provincia:</label>
    <input class="addAddressInput" type="text" id="provincia" name="provincia" required>

    <label for="cap">Cap:</label>
    <input class="addAddressInput" type="text" id="cap" name="cap" required>

    <label for="nazione">Nazione:</label>
    <input class="addAddressInput" type="text" id="nazione" name="nazione" required>

    <button id="addProductButton" type="submit" onmouseover="validateAllAddress('addAddressForm' , 'addProductButton')">Aggiungi Indirizzo</button>
</form>
    </div>

<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>
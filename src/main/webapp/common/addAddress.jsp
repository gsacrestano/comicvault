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
<script src = "scripts/validate.js"></script>

<div id="updateProduct">
<form id="addAddressForm" action="${pageContext.request.contextPath}/common/AddAddressServlet" method="post">
    <h1 style="color: #0d0d0d">Aggiungi Indirizzo</h1>
    <label id="addAddressLabel" for="via">Via:</label>
    <input class="addAddressInput" type="text" id="via" name="via" required>
    <br/>
    <span id = "errorAddress"></span>
    <br>
    <label for="citta">Citta:</label>
    <input class="addAddressInput" type="text" id="citta" name="citta" required>
    <br/>
    <span id = "errorCitta"></span>
    <br>

    <label for="provincia">Provincia:</label>
    <input class="addAddressInput" type="text" id="provincia" name="provincia" required>
    <br/>
    <span id = "errorProv"></span>
    <br>

    <label for="cap">Cap:</label>
    <input class="addAddressInput" type="text" id="cap" name="cap" required>
    <br/>
    <span id = "errorCap"></span>
    <br>

    <label for="nazione">Nazione:</label>
    <input class="addAddressInput" type="text" id="nazione" name="nazione" required>
    <br/>
    <span id = "errorNaz"></span>
    <br>

    <button id="addProductButton" type="submit" onmouseover="validateAllAddress('addAddressForm' , 'addProductButton')">Aggiungi Indirizzo</button>
</form>
    </div>

<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>
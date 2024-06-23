<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="../css/style.css">
    <meta charset="UTF-8">
    <title>Aggiungi Prodotto</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<script src="scripts/validate.js"></script>
<form id="addProductForm" action="${pageContext.request.contextPath}/admin/AddProductServlet" method="post" enctype="multipart/form-data" onsubmit="removeSpecial()">
    <h1>Aggiungi Prodotto</h1>
    <label id="addProductLabel" for="nome">Nome:</label>
    <input class="addProductInput" type="text" id="nome" name="nome" required>

    <label for="descrizione">Descrizione:</label>
    <textarea style="resize: none" id="descrizione" name="descrizione" required></textarea>

    <label for="isbn">ISBN:</label>
    <input  class="addProductInput" type="text" id="isbn" name="isbn" required>

    <label for="prezzo">Prezzo:</label>
    <input class="addProductInput" type="number" step="0.01" id="prezzo" name="prezzo" required  oninput="validatePrice('prezzo' , 'spanPrezzo')">
    <br>
    <span style="color: #bd2130" id = "spanPrezzo"></span>
    <br>

    <label for="quantita">Quantit&agrave:</label>
    <input class="addProductInput" type="number" id="quantita" name="quantita" required oninput="validatePrice('quantita' , 'spanQuantita')">
    <br>
    <span style="color: #bd2130" id = "spanQuantita"></span>
    <br>

    <label for="image_path">Carica Immagine:</label>
    <input class="addProductInput"  type="file" id="image_path" name="image_path" accept="image/*" required>

    <button id="addProductButton" type="submit" onmouseover="validateQuantPrice('prezzo' , 'quantita' , 'addProductButton')">Aggiungi Prodotto</button>
</form>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>
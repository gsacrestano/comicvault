<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Aggiungi Prodotto</title>
</head>
<body>
<h1>Aggiungi Prodotto</h1>
<form action="${pageContext.request.contextPath}/admin/AddProductServlet" method="post" enctype="multipart/form-data">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required><br><br>

    <label for="descrizione">Descrizione:</label>
    <textarea id="descrizione" name="descrizione" required></textarea><br><br>

    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" required><br><br>

    <label for="prezzo">Prezzo:</label>
    <input type="number" step="0.01" id="prezzo" name="prezzo" required><br><br>

    <label for="quantita">Quantità:</label>
    <input type="number" id="quantita" name="quantita" required><br><br>

    <label for="image_path">Carica Immagine:</label>
    <input type="file" id="image_path" name="image_path" accept="image/*" required><br><br>

    <button type="submit">Aggiungi Prodotto</button>
</form>
</body>
</html>

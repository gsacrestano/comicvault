<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Aggiungi Prodotto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        h1 {
            color: #F23A29;
            text-align: center;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #F23A29;
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="file"] {
            padding: 3px;
        }
        button {
            background-color: #F23A29;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #F23A29;
        }
    </style>
</head>
<body>

<form action="${pageContext.request.contextPath}/admin/AddProductServlet" method="post" enctype="multipart/form-data">
    <h1>Aggiungi Prodotto</h1>
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required>

    <label for="descrizione">Descrizione:</label>
    <textarea id="descrizione" name="descrizione" required></textarea>

    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" required>

    <label for="prezzo">Prezzo:</label>
    <input type="number" step="0.01" id="prezzo" name="prezzo" required>

    <label for="quantita">Quantit&agrave:</label>
    <input type="number" id="quantita" name="quantita" required>

    <label for="image_path">Carica Immagine:</label>
    <input type="file" id="image_path" name="image_path" accept="image/*" required>

    <button type="submit">Aggiungi Prodotto</button>
</form>
</body>
</html>
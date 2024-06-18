<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Prodotti</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        form {
            margin: 20px 0;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            margin-bottom: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #F23A29;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #D93D3D;
        }
    </style>
</head>

<body>

<h1>Gestione dei Prodotti</h1>

<!--Collegamento per aggiungere un nuovo prodotto -->
<h2>Aggiungi Nuovo Prodotto</h2>
<button onclick="location.href='${pageContext.request.contextPath}/admin/addProduct.jsp'">Nuovo Prodotto</button>


<!-- Tabella per visualizzare, aggiornare e cancellare prodotti esistenti -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Prezzo</th>
        <th>Descrizione</th>
        <th>ISBN</th>
        <th>Quantità</th>
        <th>Immagine</th>
        <th colspan="3">Azione</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.nome}</td>
            <td>${product.prezzo}</td>
            <td>${product.descrizione}</td>
            <td>${product.isbn}</td>
            <td>${product.quantita}</td>
            <td>${product.image_path}</td>
            <td> <button type="submit">Aggiorna</button> </td>
            <td> <button type="submit">Modifica</button> </td>
            <td> <button type="submit">Cancella</button> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="<%=request.getContextPath()%>/admin/homepage.jsp">Home</a>
</body>
</html>


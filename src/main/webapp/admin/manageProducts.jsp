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
            <td><img src="${pageContext.request.contextPath}/images/products/${product.image_path}" width="50" height="50"> (${product.image_path})</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/updateProduct.jsp" method="post">
                    <input type="hidden" name="id" value="${product.id}" />
                    <input type="hidden" name="nome" value="${product.nome}" />
                    <input type="hidden" name="descrizione" value="${product.descrizione}" />
                    <input type="hidden" name="isbn" value="${product.isbn}" />
                    <input type="hidden" name="prezzo" value="${product.prezzo}" />
                    <input type="hidden" name="quantita" value="${product.quantita}" />
                    <input type="hidden" name="image_path" value="${product.image_path}" />
                    <button type="submit">Modifica</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/DeleteProductServlet" method="post" onsubmit="return confirm('Sei sicuro di voler cancellare questo prodotto?');">
                    <input type="hidden" name="id" value="${product.id}" />
                    <button type="submit">Cancella</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="<%=request.getContextPath()%>/admin/homepage.jsp">Home</a>
</body>
</html>


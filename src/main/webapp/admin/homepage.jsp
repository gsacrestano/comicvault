<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Homepage Admin</title>
    <style>
        h1{
            text-align: center;
        }
        /* Stili CSS opzionali per migliorare l'aspetto della pagina */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .button {
            text-align: center;
            font-size: 100%;
            width: 100%;
            display: block;
            padding: 1%;
            background-color: #F23A29;
            color: #fff;
            text-decoration: none;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            margin: 1%;
        }
        .button:hover {
            background-color: #bd2130;
        }
    </style>
</head>
<body>
<h1>Benvenuto, Admin!</h1>
<div style="width: 70%; margin: 0 15%">
    <a href="<%=request.getContextPath()%>/admin/ManageOrdersServlet" class="button">Lista Ordini</a>
    <a href="<%=request.getContextPath()%>/admin/RetrieveProductsServlet" class="button">Gestisci Prodotti</a>
    <a href="<%=request.getContextPath()%>/admin/ManageUsersServlet" class="button">Lista Utenti</a>
    <a href="<%=request.getContextPath()%>/common/Logout" class="button">Logout</a>
</div>
</body>
</html>


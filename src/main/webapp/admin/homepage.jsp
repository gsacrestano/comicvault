<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Homepage Admin</title>
    <style>
        /* Stili CSS opzionali per migliorare l'aspetto della pagina */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            margin-right: 10px;
        }
        .button:hover {
            background-color: #0056b3;
        }
        .logout-button {
            background-color: #dc3545;
        }
        .logout-button:hover {
            background-color: #bd2130;
        }
    </style>
</head>
<body>
<h1>Benvenuto, Admin!</h1>
<div>
    <a href="<%=request.getContextPath()%>/admin/ManageOrdersServlet" class="button">Gestisci Ordini</a>
    <a href="addProduct.jsp" class="button">Gestisci Prodotti</a>
    <a href="<%=request.getContextPath()%>/admin/ManageUsersServlet" class="button">Gestisci Utenti</a>
    <a href="<%=request.getContextPath()%>/common/Logout" class="button">Logout</a>
</div>
</body>
</html>


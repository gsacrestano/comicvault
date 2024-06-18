<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 80%;
        }
        .container h2 {
            margin-top: 0;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: calc(100% - 10px);
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
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
    </style>
    <script src="../scripts/validate.js"></script>
</head>
<body>
<div class="container">
    <h2>Registrazione</h2>
    <img style="width: 70%; margin: 0 15%;" src="${pageContext.request.contextPath}/images/logo.jpeg" alt="Logo">
    <form id="regForm" action="${pageContext.request.contextPath}/common/RegistrationServlet" method="post" onsubmit="validate()">
        <div class="form-group">
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" required>
                <span id="errorName"></span>
            </div>
            <div class="form-group">
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome" required>
                <span id="errorLastname"></span>
            </div>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required>
            <span id="errorEmail"></span>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <button type="submit">Continua</button>
        </div>
        <a style="text-align: center" href="../index.jsp">Home</a>
        <a  style="text-align: center" href="login.jsp">Login</a>
    </form>
    <div id="errorMessages">
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && !errors.isEmpty()) {
                for (String error : errors) {
        %>
        <p class="error-message"><%= error %></p>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>

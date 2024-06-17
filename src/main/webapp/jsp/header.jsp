<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <title>Navbar con Logo</title>
    <style>
        .topnav {
            overflow: hidden;
            background-color: #D93D3D;
            display: flex;
            align-items: center;
            padding: 0 10px;
        }

        .topnav a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav img {
            height: 75px;
            width: 75px;
            margin-right: 10px;
        }

        .topnav .links {
            display: flex;
            align-items: center;
        }

        .topnav .links a {
            margin-left: 10px;
        }

        .topnav .search-container {
            margin-left: auto;
        }

        .topnav input[type=text] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
        }

        .topnav .search-container button {
            padding: 6px 10px;
            margin-top: 8px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }

        .topnav .search-container button:hover {
            background: #ccc;
        }
    </style>
</head>
<body>

<div class="topnav">
    <a href="${pageContext.request.contextPath}/index.jsp">
        <img src="${pageContext.request.contextPath}/images/logo.jpeg" alt="Logo">
    </a>

    <a href="${pageContext.request.contextPath}/index.jsp">Comic Vault</a>

    <div class="search-container">
        <form action="">
            <label>
                <input type="text" placeholder="Cerca.." name="search">
            </label>
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>

    <div class="links">
        <a href="${pageContext.request.contextPath}/common/login.jsp">Accedi</a>
        <a href="${pageContext.request.contextPath}/index.jsp">Carrello</a>
    </div>
</div>

</body>
</html>
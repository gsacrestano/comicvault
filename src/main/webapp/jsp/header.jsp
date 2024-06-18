<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <base href="<%=request.getContextPath()%>/">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <title>Navbar con Logo</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #D93D3D;
            display: flex;
            align-items: center;
            padding: 0 10px;
            flex-wrap: wrap;
        }

        .topnav a {
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
            display: flex;
            align-items: center;
        }

        .topnav input[type=text] {
            padding: 6px;
            font-size: 17px;
            border: none;
        }

        .topnav .search-container button {
            padding: 6px 10px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }

        .topnav .search-container button:hover {
            background: #ccc;
        }

        @media (max-width: 768px) {
            .topnav {
                flex-direction: column;
                align-items: flex-start;
            }

            .topnav .search-container {
                width: 100%;
                margin-left: 0;
                margin-top: 10px;
            }

            .topnav .search-container form {
                width: 100%;
                display: flex;
            }

            .topnav input[type=text] {
                flex: 1;
            }

            .topnav .links {
                width: 100%;
                justify-content: space-between;
                margin-top: 10px;
            }

            .topnav .links a {
                margin-left: 0;
            }
        }
    </style>
</head>
<body>

<div class="topnav">
    <a href="index.jsp">
        <img src="images/logo.jpeg" alt="Logo">
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

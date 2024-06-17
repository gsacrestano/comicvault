<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleError.css">

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        footer {
            padding: 20px 0;
            text-align: center;
        }

        .footer-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }

        .footer-logo img {
            width: 100px;
            height: auto;
            margin-right: 20px;
            vertical-align: middle;
        }

        .footer-logo h2 {
            margin: 0;
            font-size: 40px;
            vertical-align: middle;
        }

        .footer-nav {
            list-style-type: none;
            padding: 0;
            margin: 0;
            text-align: right;
        }

        .footer-nav li {
            display: inline-block;
            margin-left: 20px;
        }

        .footer-nav li:first-child {
            margin-left: 0;
        }

        .footer-nav li a {
            color: #fff;
            text-decoration: none;
            font-size: 30px;
        }

        .footer-nav li a:hover {
            text-decoration: underline;
        }

        .footer-copyright {
            margin-top: 20px;
            font-size: 14px;
            opacity: 0.8;
        }
    </style>
    <title>Footer</title>
</head>
<body>
<footer>
    <div class="footer-container">
        <div class="footer-logo">
            <img src="Images/logo.jpeg" alt="Logo">
            <h2>Comic Vault</h2>
        </div>
        <ul class="footer-nav">
            <li><a href="#">Manga</a></li>
            <li><a href="#">Comics</a></li>
            <li><a href="#">Chi siamo</a></li>
        </ul>
    </div>
    <p class="footer-copyright">&#169; ComicVault</p>
</footer>
</body>
</html>

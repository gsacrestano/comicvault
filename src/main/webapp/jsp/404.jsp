<%--
  Created by IntelliJ IDEA.
  User: giulio
  Date: 21/05/24
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/styleError.css">
    <title>Osps, problema?</title>

</head>

<body>

<nav>
    <ul class="selected">
        <li> <img alt="logo" src="../Images/logo.jpeg" width="10%" height="10px"></li>
        <li><a>Comic Vault </a></li>
        <li><a id="ricerca" style="float:right; margin-right: .5%;">Carrello</a></li>
        <li><a style= " float:right; margin-right: .5%;">Accedi</a></li>
        <li><button style="float:right; margin-right: .5%;">Cerca</button></li>
        <li><input type="text" placeholder="Cosa cerchi?" style=" float:right; margin-right: .5%;"></li>
    </ul>

</nav>
<div id="container">
    <h1> Purtroppo la pagina che cerchi non è in questo fumetto</h1>

    <p> <img alt="Personaggio che legge 404" src="../Images/Error.jpg" width="400" height="300"></p>

    <p>Nel mentre vedi quali sono le ultime novità nel nostro sito, clicca per tornare alla home.</p>
    <a href="../index.jsp"> <button id="button"> Home </button>
    </a>

</div>


<footer>
    <img alt="logo" src="../Images/logo.jpeg">
    <h2>Comic Vault</h2>
    <ul>
        <li> Manga</li>
        <li> Comics</li>
        <li> Chi siamo</li>
    </ul>
</footer>





</body>

</html>

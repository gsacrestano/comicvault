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

<jsp:include page="header.jsp"/>

<div id="container">
    <h1> Purtroppo la pagina che cerchi non è in questo fumetto</h1>
    <p> <img id ="imgError" alt="Personaggio che legge 404" src="../Images/Error.jpg" width="400" height="300"></p>
    <p>Nel mentre vedi quali sono le ultime novità nel nostro sito, clicca per tornare alla home.</p>
    <a href="../index.jsp"> <button id="button"> Home </button></a>
</div>

<jsp:include page="footer.jsp"/>




</body>

</html>

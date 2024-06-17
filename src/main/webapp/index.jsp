<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="errors/404.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <title>JSP - Hello World</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="jsp/header.jsp"/>
<p><img  style="width: 100%" src="Images/banner.webp" alt="Banner"></p>

<h1>Novità</h1>
<div class="viewer">
  <div class="card">
    <p> <img src="images/logo.jpeg"></p>
    <p> Gioco </p>
    <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
  </div>
  <div class="card">
    <p> <img src="images/logo.jpeg"></p>
    <p> Gioco </p>
    <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
  </div>
  <div class="card">
    <p> <img src="images/logo.jpeg"></p>
    <p> Gioco </p>
    <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
  </div>
<h1>Più venduti </h1>
  <div class="card">
    <p> <img src="images/logo.jpeg"></p>
    <p> Gioco </p>
    <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
  </div>
  <div class="card">
    <p> <img src="images/logo.jpeg"></p>
    <p> Gioco </p>
    <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
  </div>
  <div class="card">
    <p> <img src="images/logo.jpeg"></p>
    <p> Gioco </p>
    <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
  </div>
<h1> Prossime Uscite </h1>
<div class="card">
  <p> <img src="images/logo.jpeg"></p>
  <p> Gioco </p>
  <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
</div>
<div class="card">
  <p> <img src="images/logo.jpeg"></p>
  <p> Gioco </p>
  <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
</div>
<div class="card">
  <p> <img src="images/logo.jpeg"></p>
  <p> Gioco </p>
  <a href="jsp/productDetails.jsp"> <button> Compra</button></a>
</div>
</div>



<jsp:include page="jsp/footer.jsp"/>
</body>
</html>
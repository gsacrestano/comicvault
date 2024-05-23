<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="jsp/404.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <title>JSP - Hello World</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="jsp/header.jsp"/>
<jsp:include page="jsp/carousel.jsp"/>

<h1>Novità</h1>
<div class="viewer">
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
</div>
<h1>Più venduti </h1>
<div class="viewer">
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
</div>
<h1> Prossime Uscite </h1>
<div class="viewer">
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
  <div class="card">
    <p> <img src="Images/logo.jpeg"></p>
    <p> Gioco </p>
    <button> Compra</button>
  </div>
</div>



<jsp:include page="jsp/footer.jsp"/>
</body>
</html>
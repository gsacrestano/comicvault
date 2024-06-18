<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Prodotto</title>
</head>
<body>
<h2>Modifica Prodotto</h2>

<form action="${pageContext.request.contextPath}/admin/UpdateProductServlet" method="post">
    <input type="hidden" name="id" value="${param.id}" />

    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" value="${fn:escapeXml(param.nome)}" /><br/>

    <label for="descrizione">Descrizione:</label><br/>
    <textarea id="descrizione" name="descrizione" rows="4" cols="50">${fn:escapeXml(param.descrizione)}</textarea><br/>

    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" value="${param.isbn}" /><br/>

    <label for="prezzo">Prezzo:</label>
    <input type="number" id="prezzo" name="prezzo" value="${param.prezzo}" /><br/>

    <label for="quantita">Quantità:</label>
    <input type="number" id="quantita" name="quantita" value="${param.quantita}" /><br/>

    <label for="image_path">Percorso dell'immagine:</label>
    <input type="text" id="image_path" name="image_path" value="${param.image_path}" /><br/>

    <input type="submit" value="Aggiorna Prodotto" />
</form>

</body>
</html>

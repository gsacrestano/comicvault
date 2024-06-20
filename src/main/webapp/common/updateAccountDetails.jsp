<%@ page import="model.bean.UtenteBean" %>
<%@ page session="true" %>
<%
    UtenteBean utente = (UtenteBean) session.getAttribute("account");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Modifica Dati Personali</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<script src="scripts/validate.js"></script>
<h1>Modifica Dati Personali</h1>
<form id="updateProduct" action="${pageContext.request.contextPath}/common/UpdateAccountServlet" method="post">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" value="<%= utente.getNome() %>" onblur="validateNome('updateProduct')"><br><br>
    <span id="errorName"></span>
    <label for="cognome">Cognome:</label>
    <input type="text" id="cognome" name="cognome" value="<%= utente.getCognome() %>" onblur="validateCognome('updateProduct')"><br><br>
    <span id="errorLastname"></span>
    <label for="phone">Telefono:</label>
    <input type="text" id="phone" name="phone" value="<%= utente.getTelefono() %>" onblur="validatePhone('updateProduct')"><br><br>
    <input type="submit" value="Aggiorna">
    <span id="errorPhone"></span>
</form>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>

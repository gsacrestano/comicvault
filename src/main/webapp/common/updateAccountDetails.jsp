<%@ page import="model.bean.UtenteBean" %>
<%@ page session="true" %>
<%
    UtenteBean utente = (UtenteBean) session.getAttribute("account");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modifica Dati Personali</title>
</head>
<body>
<h1>Modifica Dati Personali</h1>
<form action="${pageContext.request.contextPath}/common/UpdateAccountServlet" method="post">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" value="<%= utente.getNome() %>"><br><br>
    <label for="cognome">Cognome:</label>
    <input type="text" id="cognome" name="cognome" value="<%= utente.getCognome() %>"><br><br>
    <label for="telefono">Telefono:</label>
    <input type="text" id="telefono" name="telefono" value="<%= utente.getTelefono() %>"><br><br>
    <input type="submit" value="Aggiorna">
</form>
</body>
</html>

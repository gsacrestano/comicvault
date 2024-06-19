<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Homepage Admin</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<h1>Benvenuto, ${account.nome}!</h1>

<div style="width: 70%; margin: 0 15%">
    <a href="<%=request.getContextPath()%>/common/updateAccountDetails.jsp" class="aAdminPage">Dati Personali</a>
    <a href="<%=request.getContextPath()%>/common/manageAddresses.jsp" class="aAdminPage">Gestione Indirizzi</a>
    <a href="<%=request.getContextPath()%>/common/ordersHistory.jsp" class="aAdminPage">Cronologia Ordini</a>
    <a href="<%=request.getContextPath()%>/common/Logout" class="aAdminPage">Logout</a>
</div>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>


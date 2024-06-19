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
<jsp:include page="../jsp/header.jsp"/>
<h1>Benvenuto, Admin!</h1>
<div style="width: 70%; margin: 0 15%">
    <a href="<%=request.getContextPath()%>/admin/ManageOrdersServlet" class="aAdminPage">Lista Ordini</a>
    <a href="<%=request.getContextPath()%>/admin/RetrieveProductsServlet" class="aAdminPage">Gestisci Prodotti</a>
    <a href="<%=request.getContextPath()%>/admin/ManageUsersServlet" class="aAdminPage">Lista Utenti</a>
    <a href="<%=request.getContextPath()%>/common/Logout" class="aAdminPage">Logout</a>
</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>


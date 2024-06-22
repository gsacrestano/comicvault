<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../css/style.css">
    <meta charset="UTF-8">
    <title>Lista Ordini</title>
    <!-- Includi i tuoi stili CSS e script JavaScript qui -->
</head>
<body>

<jsp:include page="/jsp/header.jsp"/>

<h2>Lista Ordini</h2>

<!-- Filtri -->
<div>
    <label for="email-filter">Email:</label>
    <input type="text" id="email-filter" onkeyup="filterTable()">

    <label for="start-date-filter">Data Inizio:</label>
    <input type="date" id="start-date-filter" onchange="filterTable()">

    <label for="end-date-filter">Data Fine:</label>
    <input type="date" id="end-date-filter" onchange="filterTable()">
</div>

<table id="orders-table" border="1">
    <thead>
    <tr>
        <th>ID Ordine</th>
        <th>Email</th>
        <th>Data</th>
        <th>Totale</th>
        <th>Dettagli</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.emailUtente}</td>
            <td>${order.data}</td>
            <td>${order.totale}</td>
            <td><button onclick="mostraDettagli(${order.id});">Dettaglio</button></td>
        </tr>
        <tr id="dettagli-${order.id}" style="display: none;">
            <td colspan="5">
                <h3>Dettagli Ordine ${order.id}</h3>
                <p><strong>Nome Utente:</strong> ${order.nomeUtente} ${order.cognomeUtente}</p>
                <p><strong>Email Utente:</strong> ${order.emailUtente}</p>
                <p><strong>Indirizzo:</strong> ${order.indirizzo}</p>
                <p><strong>Data:</strong> ${order.data}</p>
                <p><strong>Totale:</strong> ${order.totale}</p>
                <table border="1">
                    <thead>
                    <tr>
                        <th>ID Prodotto</th>
                        <th>Nome Prodotto</th>
                        <th>Prezzo Unitario</th>
                        <th>Quantità</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${order.prodotti}" var="prodotto">
                        <tr>
                            <td>${prodotto.id}</td>
                            <td>${prodotto.nome}</td>
                            <td>${prodotto.prezzo}</td>
                            <td>${prodotto.quantita}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hr>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="${pageContext.request.contextPath}/admin/homepage.jsp">Torna alla Home Admin</a>

<!-- Script JavaScript per mostrare/nascondere i dettagli -->
<script>
    function mostraDettagli(idOrdine) {
        var dettagliElement = document.getElementById("dettagli-" + idOrdine);
        if (dettagliElement.style.display === "none") {
            dettagliElement.style.display = "block";
        } else {
            dettagliElement.style.display = "none";
        }
    }
</script>

<!-- Script Javascript per il filtraggio della tabella -->
<script>
    function filterTable() {
        // Recupera i valori dei filtri
        var emailFilter = document.getElementById("email-filter").value.toLowerCase();
        var startDateFilter = document.getElementById("start-date-filter").value;
        var endDateFilter = document.getElementById("end-date-filter").value;

        // Recupera tutte le righe della tabella
        var table = document.getElementById("orders-table");
        var tr = table.getElementsByTagName("tr");

        // Itera su tutte le righe della tabella
        for (var i = 1; i < tr.length; i++) {

            // Recupera le celle della riga corrente
            var tdEmail = tr[i].getElementsByTagName("td")[1];
            var tdDate = tr[i].getElementsByTagName("td")[2];

            if (tdEmail || tdDate) {
                var emailValue = tdEmail.textContent || tdEmail.innerText;
                var dateValue = tdDate.textContent || tdDate.innerText;

                // Determina se la riga deve essere mostrata
                var showRow = true;

                if (emailFilter && emailValue.toLowerCase().indexOf(emailFilter) === -1) {
                    showRow = false;
                }

                if (startDateFilter && new Date(dateValue) < new Date(startDateFilter)) {
                    showRow = false;
                }

                if (endDateFilter && new Date(dateValue) > new Date(endDateFilter)) {
                    showRow = false;
                }


                if (showRow) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";

                }
            }
        }
    }
</script>


<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>

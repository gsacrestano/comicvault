function sendSliderValue(contextPath) {
    const xhr = new XMLHttpRequest();
    const timestamp = new Date().getTime(); // Aggiungi un timestamp per evitare il caching
    const searchValue = document.getElementById('search').value; // Assumi che ci sia un input con id="search"
    const str = `${contextPath}/common/NumProductServlet?name=` + searchValue + "&timestamp=" + timestamp;
    xhr.open('GET', str, true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('num').innerHTML = xhr.responseText;
        }
    };

    xhr.send();
}

document.addEventListener('DOMContentLoaded', function() {
    const priceSlider = document.getElementById('priceSlider');
    const priceOutput = document.getElementById('price-output');

    // Funzione per aggiornare il testo del prezzo
    function updatePriceOutput() {
        const value = priceSlider.value;
        priceOutput.innerHTML =  "$0-"+"$"+value;
    }

    // Aggiornare il testo del prezzo quando l'utente interagisce con lo slider
    priceSlider.addEventListener('input', updatePriceOutput);

    // Impostare il testo iniziale del prezzo
    updatePriceOutput();
});
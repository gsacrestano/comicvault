const nameOrLastnamePattern = /^[A-Za-z]+$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const phonePattern = /^([0-9]{10})$/;
const nameErrorMessage = "Il nome deve avere solo lettere";
const lastnameErrorMessage = "Il cognome deve avere solo lettere";
const emailErrorMessage = "La mail dovrebbe essere del tipo username@domain.ext";
const numberErrorMessage = "Un numero valido ha 10 cifre";
let count = 1;


function validateFormElem(formElem, pattern, span, message) {
    if(formElem.value.match(pattern)){
        formElem.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    }
    formElem.classList.add("error");
    span.innerHTML = message;
    span.style.color = "red";
    return false;
}

function validateNome(str) {
    let valid = true;
    let form = document.getElementById(str);

    let spanName = document.getElementById("errorName");
    if (!validateFormElem(form.nome, nameOrLastnamePattern, spanName, nameErrorMessage)) {
        valid = false;

        return valid;
    }
}
function validateCognome(str) {
    let valid = true;
    let form = document.getElementById(str);

    let spanLastname = document.getElementById("errorLastname");
    if (!validateFormElem(form.cognome, nameOrLastnamePattern, spanLastname, lastnameErrorMessage)) {
        valid = false;
    }



    return valid;
}
function validateMail(str) {
    let valid = true;
    let form = document.getElementById(str);

    let spanEmail = document.getElementById("errorEmail");
    if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)){
        valid = false;
    }

    return valid;
}
function validatePhone(str) {
    let valid = true;
    let form = document.getElementById(str);
    let spanPhone = document.getElementById("errorPhone");
    let phoneElement = document.getElementById("phone");
    let phoneValue = phoneElement.value.toString();

    console.log(phoneValue);

    // Verifica se il campo del telefono è vuoto o se non passa la validazione del pattern
    if (phoneValue!=="" && phoneValue !== "null" && !validateFormElem(phoneElement, phonePattern, spanPhone, numberErrorMessage)) {
        valid = false;
    }

    return valid;
}


function removeSpecial (){
    document.getElementById("nome").value = escapeHtml(   document.getElementById("nome").value );
    document.getElementById("descrizione").value = escapeHtml(   document.getElementById("descrizione").value );
    document.getElementById("isbn").value = escapeHtml(   document.getElementById("isbn").value );
    document.getElementById("prezzo").value = escapeHtml(   document.getElementById("prezzo").value );
    document.getElementById("quantita").value = escapeHtml(   document.getElementById("quantita").value );

}
function escapeHtml(str) {
    const entityMap = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#39;',
        '/': '&#x2F;',
        '`': '&#x60;',
        '=': '&#x3D;'
    };

    return String(str).replace(/[&<>"'`=\/]/g, function (s) {
        return entityMap[s];
    });
}


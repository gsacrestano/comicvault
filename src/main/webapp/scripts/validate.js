const nameOrLastnamePattern = /^[A-Za-z]+$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const phonePattern = /^([0-9]{3}-[0-9]{7})$/;
const nameErrorMessage = "A valid name should contain only letters";
const lastnameErrorMessage = "A valid lastname should contain only letters";
const emailErrorMessage = "A valid email should be in the form username@domain.ext";
const numberErrorMessage = "A valid number should be in the form ###-#######";
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

function validate() {
    let valid = true;
    let form = document.getElementById("regForm");

    let spanName = document.getElementById("errorName");
    if(!validateFormElem(form.nome, nameOrLastnamePattern, spanName, nameErrorMessage)){
        valid = false;
    }
    let spanLastname = document.getElementById("errorLastname");
    if (!validateFormElem(form.cognome, nameOrLastnamePattern, spanLastname, lastnameErrorMessage)){
        valid = false;
    }
    let spanEmail = document.getElementById("errorEmail");
    if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)){
        valid = false;
    }

    return valid;
}

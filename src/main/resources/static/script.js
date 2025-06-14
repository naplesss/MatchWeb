const password = document.getElementById('password');
const vPassword = document.getElementById('vPassword');
const passwordError = document.getElementById('passwordError');

function validatePassword() {
    if (password.value !== vPassword.value) {
        passwordError.textContent = 'Passwords do not match';
        return false;
    } else {
        passwordError.textContent = '';
        return true;
    }
}

vPassword.addEventListener('blur', validatePassword);

// Dynamic team loading based on sport selection
const sportSelect = document.getElementById('sport');
const teamSelect = document.getElementById('sportcuore');


// sportSelect.addEventListener('change', function() {
//     const selectedSport = this.value;
//     teamSelect.innerHTML = '<option value="" selected disabled>Choose a team</option>';
//
//     if (selectedSport) {
//         teamSelect.disabled = false;
//         teamsBySport[selectedSport].forEach((team, index) => {
//             const option = document.createElement('option');
//             option.value = index + 1; // or use actual team IDs
//             option.textContent = team;
//             teamSelect.appendChild(option);
//         });
//     } else {
//         teamSelect.disabled = true;
//     }
// });

function mostraPassword(id) {
    var password = document.getElementById(id);
    if (password.type === "password") {
        password.type = "text";
    } else {
        x.type = "password";
    }
}

function matchpassword(passwordId, checkId){
    var password = document.getElementById(passwordId).value;
    var check = document.getElementById(checkId).value;

    if (password == check){
        //sarà verde
    }
    else{
        //sarà rosso
    }
}

function mostraMessaggio(){
    var password = document.getElementById('password');
    var numeriB = document.getElementById('numeri');//la barra dei numeri
    var specialiB = document.getElementById('speciali');//barra dei caratteri speciali
    var lunghezzaB = document.getElementById('lunghezza');//barra della lunghezza
    var bool = true;


    var numeri=/0-9/g;
    let matches = password.value.match(numeri);
    if (matches && matches.length===2){
        numeri.classList.remove("invalid");
        numeri.classList.add("valid");
    }
    else{
        numeri.classList.remove("valid");
        numeri.classList.add("invalid");
        bool = false;
    }

    var speciali =  /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
    matches = password.value.match(speciali);
    if (matches && matches.length===1) {
        speciali.classList.remove("invalid");
        speciali.classList.add("valid");
    }
    else{
        speciali.classList.remove("valid");
        speciali.classList.add("invalid");
        bool = false;
    }

    if(password.value.length===9){
        lunghezzaB.classList.remove("invalid");
        lunghezzaB.classList.add("valid");
    }
    else{
        //no, pingo di rosso
        bool = false;
    }

    return bool;

}
function nascondiMessaggio(){
    document.getElementById("messaggio").style.display = "none";
}
function checkPassword(){
    document.getElementById("messaggio").style.display = "block";
}
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



//mostra la password o la nasconde
function mostraPassword(id) {
    var password = document.getElementById(id);
    if (password.type === "password") {
        password.type = "text";
    } else {
        password.type = "password";
    }
}
//controlla l'uguaglianza delle password
function matchpassword(passwordId, checkId){
    var password = document.getElementById(passwordId).value;
    var check = document.getElementById(checkId).value;
    var uguale = document.getElementById('uguale');
    var bool = true;

    if (password === check){
        uguale.classList.remove('invalid');
        uguale.classList.add('valid');
        uguale.textContent = 'le password sono uguali';
    }
    else{
        uguale.classList.remove('valid');
        uguale.classList.add('invalid');
        uguale.textContent = 'le password non sono uguali';
        bool = false;
    }
    return bool;
}
//controlla che la password rispetta i parametri
function checkPassword(){
    var password = document.getElementById('password');
    var numeriB = document.getElementById('numeri');//la barra dei numeri
    var specialiB = document.getElementById('speciali');//barra dei caratteri speciali
    var lunghezzaB = document.getElementById('lunghezza');//barra della lunghezza
    var bool = true;


    var numeri=/[0-9]/g;
    let matches = password.value.match(numeri);
    if (matches && matches.length===2){
        numeriB.classList.remove("invalid");
        numeriB.classList.add("valid");
    }
    else{
        numeriB.classList.remove("valid");
        numeriB.classList.add("invalid");
        bool = false;
    }

    var speciali =  /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
    matches = password.value.match(speciali);
    if (matches && matches.length===1) {
        specialiB.classList.remove("invalid");
        specialiB.classList.add("valid");
    }
    else{
        specialiB.classList.remove("valid");
        specialiB.classList.add("invalid");
        bool = false;
    }

    if(password.value.length===9){
        lunghezzaB.classList.remove("invalid");
        lunghezzaB.classList.add("valid");
    }
    else{
        lunghezzaB.classList.remove("valid");
        lunghezzaB.classList.add("invalid");
        bool = false;
    }

    return bool;

}
//nasconde il messaggio
function nascondiMessaggio(id){
    document.getElementById(id).style.display = "none";
}
//mostra il messaggio
function mostraMessaggio(id){
    document.getElementById(id).style.display = "block";
}
//aggiunge le squadre al select
function mostraSquadre(){
    var sport = document.getElementById('sport').value;
    var teamSelect = document.getElementById('sportcuore');
    var messaggio = document.getElementById('container');
    if(sport === 'lacrosse'){
        messaggio.style.display = "none";
    }
    else {
        messaggio.style.display = "block";
    }
        fetch("/getTeams", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({sport: sport}),
        }).then(function (response) {
            if (response.status !== 200) {
                throw new Error("ERRORE CARICAMENTO SQUADRE");

            }
            return response.json();

        }).then(function (json) {
            teamSelect.innerHTML = '';
            json.forEach(team => {
                var option = document.createElement('option');
                option.value = team.nomesquadra;
                option.textContent = team.nomesquadra;
                teamSelect.appendChild(option);
            });
        }).catch(error => {
            console.error(error);
        });


}
//invia i dati al server e restituisce "teoricamente" i risultati
function inviamo(){
    var righe = document.getElementsByTagName("tr");
    fetch("/scommesse", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `pronostico1=${encodeURIComponent(document.getElementById("pronostico1").value)}&pronostico2=${encodeURIComponent(document.getElementById("pronostico2").value)}`
    }).then(function (response) {
        if (!response.ok) {
            throw new Error("ERRORE CARICAMENTO PARTITE");

        }
        return response.json();

    }).then(function (risultati) {
        var risultRig = document.createElement('tr');
        var risultCol = document.createElement('td');
        risultCol.colSpan = 3;
        risultCol.textContent = "Risultati"+ risultati.join("-");
        risultRig.appendChild(risultCol);

        document.querySelector('form').after(risultRig);
    }).catch(error => {
        console.error(error);
        alert("SI è VERIFICATTO UN ERRORE: "+ error.message);
    });
}




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


sportSelect.addEventListener('change', function() {
    const selectedSport = this.value;
    teamSelect.innerHTML = '<option value="" selected disabled>Choose a team</option>';

    if (selectedSport) {
        teamSelect.disabled = false;
        teamsBySport[selectedSport].forEach((team, index) => {
            const option = document.createElement('option');
            option.value = index + 1; // or use actual team IDs
            option.textContent = team;
            teamSelect.appendChild(option);
        });
    } else {
        teamSelect.disabled = true;
    }
});

// load all pets for the owners
async function loadAccountInfo() {
    try {
        const response = await fetch('/api/owner');

        if(!response.ok){
            throw new Error(`Failed to load Owner account information (status ${response.status})`)
        }
        const owner = await response.json();


        console.log('Owner Account: ', owner);


        document.getElementById('username').textContent =owner.username;
        document.getElementById('email').textContent = owner.email;


    } catch (error) {
        console.error('Error loading Owner account information:', error);
        document.getElementById('account-info').innerHTML =
            "<li>Error loading account information.</li>"
    }
}

loadAccountInfo();
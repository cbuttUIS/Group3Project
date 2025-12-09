
// Load all pets for the owner and display them as links
async function loadPets() {
    try {
        const response = await fetch('/api/pets');

        if(!response.ok){
            throw new Error(`Failed to load pets (status ${response.status})`)
        }
        const pets = await response.json();


        const list = document.getElementById('pets-list');
        list.innerHTML = '';

        if(pets.length == 0){
            list.innerHTML = "<li>No Pets added yet.</li>";
            return;
        }

        pets.forEach(pet => {
            const li = document.createElement('li');
            const link = document.createElement('a');
            link.href = `/pets/${pet.id}`;
            link.textContent = `${pet.petName} (${pet.petType})`;
            li.appendChild(link);
            list.appendChild(li);
        });
    } catch (error) {
        console.error('Error loading pets:', error);
        document.getElementById('pets-list').innerHTML =
            "<li>Error loading pets.</li>"
    }
}

loadPets();
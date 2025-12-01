
// Load all pets for the owner and display them as links
async function loadPets() {
    try {
        const response = await fetch('/api/pets');
        const pets = await response.json();

        const list = document.getElementById('pets-list');
        list.innerHTML = '';

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
    }
}

loadPets();
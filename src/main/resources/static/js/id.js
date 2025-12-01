
async function loadPetProfile() {

    //debugging
    console.log(window.location.href);
    console.log(window.location.pathname);

    const petId = window.location.pathname.split("/").pop();

    if(!petId || petId == "undefined"){
        console.error("No pet ID found in URL")
        return;
    }

    console.log("Loading pet ID:", petId);


    try{
        const response = await fetch(`/api/pets/${petId}`);
        const pet = await response.json();

        document.getElementById('petName').textContent = pet.petName;
        document.getElementById('petType').textContent = pet.petType;
        document.getElementById('birthYear').textContent = pet.birthYear;
        document.getElementById('birthMonth').textContent = pet.birthMonth;
        document.getElementById('petAge').textContent = pet.petAge;
        document.getElementById('healthConcerns').textContent = pet.healthConcerns || 'None';

    } catch (error) {
        console.error("Error loading pet profile: ", error);
    }
}

loadPetProfile();
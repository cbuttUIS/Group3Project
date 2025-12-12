

async function loadPetProfile() {
    const id = window.location.pathname.split("/").pop();


    try {
        // Fetch pet info
        const petResponse = await fetch(`/api/pets/${id}`);
        const pet = await petResponse.json();

        // Fetch pet profile
        const profileResponse = await fetch(`/api/pet-profiles/${id}`);
        const profile = await profileResponse.json();

        // Fetch events for this pet
        const eventsResponse = await fetch(`/api/pets/${id}/events`);
        const events = await eventsResponse.json();

        console.log("Loading page for pet ID:", id);
        console.log('Pet data:', pet);
        console.log('Pet profile:', profile);
        console.log('Pet events:', events);

        // Display pet info
        document.getElementById('petName').textContent = pet.petName;
        document.getElementById('petType').textContent = pet.petType;
        document.getElementById('birthYear').textContent = pet.birthYear;
        document.getElementById('birthMonth').textContent = pet.birthMonth;
        document.getElementById("age").textContent = pet.age;

        // Display health concerns
        document.getElementById('healthConcerns')
            .textContent = profile ? profile.healthConcerns || 'None' : 'None';

        renderEvents(events);

    } catch (error) {
        console.error("Error loading pet profile:", error);
    }

    deletePet.addEventListener("click", async () => {
        const id = window.location.pathname.split("/").pop();
        try {
            await fetch(`/api/pets/${id}`, { method: "DELETE" });
            window.location.href = "/dashboard";
        } catch (error) {
            console.error("Error deleting pet:", error);
        }
    });



    function renderEvents(events) {
        const eventsList = document.getElementById('eventsList');
        eventsList.innerHTML = '';

        if (!events || events.length === 0) {
            eventsList.innerHTML = '<li>No events</li>';
        } else {
            events.forEach(event => {
                const li = document.createElement('li');
                const start = new Date(event.eventStartTime);
                const end = new Date(event.eventEndTime);
                li.textContent = `${event.eventName} — ${formatDateTime(start)} to ${formatDateTime(end)}`;
                eventsList.appendChild(li);
            });

        }

    }


    // elements needed to set up health concern editing on profile pagfe
    const editBtn = document.getElementById('edit');
    const saveBtn = document.getElementById('save');
    const textArea = document.getElementById('healthConcerns');
    const saveMessage = document.getElementById('saveMessage');


    // Enable editing
    editBtn.addEventListener('click', () => {
        textArea.removeAttribute('readonly');
        editBtn.style.display = 'none';
        saveBtn.style.display = 'inline-block';
        saveMessage.textContent = '';
        textArea.focus(); 
    });


    // Disable editing and show saved message
    saveBtn.addEventListener('click', async () => {

        const id = window.location.pathname.split("/").pop();
        const updatedText = textArea.value;

        try {
            const response = await fetch(`/api/pet-profiles/${id}/health-concerns`, {
                method: 'PUT',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ healthConcerns: updatedText })
            });

            if (!response.ok) {
                throw new Error("Failed to update health concerns");
            }

            saveMessage.textContent = "Changes saved!";
        } catch (error) {
            console.error(error);
            saveMessage.textContent = "Error saving changes";
        }

        textArea.setAttribute('readonly', 'readonly');
        editBtn.style.display = 'inline-block';
        saveBtn.style.display = 'none';
    });


    function formatDateTime(dt) {
        return `${dt.getMonth() + 1}/${dt.getDate()}/${dt.getFullYear()} ${dt.getHours()}:${dt.getMinutes().toString().padStart(2, '0')}`;
    }

}

loadPetProfile();

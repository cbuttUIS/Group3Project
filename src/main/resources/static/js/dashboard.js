
/* Load all pets and create dashboard
 */
async function loadPets() {
    try {
        const response = await fetch('/api/pets');

        if (!response.ok) throw new Error("Failed to load pets");

        const pets = await response.json();

        // PET LIST ON DASHBOARD
        const list = document.getElementById('pets-list');
        list.innerHTML = '';

        if (pets.length === 0) {
            list.innerHTML = "<li>No pets added yet.</li>";
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

        // Fill dropdown for events
        fillPetSelectForEvents(pets);

    } catch (error) {
        console.error(error);
        document.getElementById('pets-list').innerHTML = "<li>Error loading pets</li>";
    }
}

/* loads all event across pets
 */
async function loadEvents() {
    try {
        const petsResponse = await fetch('/api/pets');
        if (!petsResponse.ok) throw new Error("Failed to load pets for events");

        const pets = await petsResponse.json();
        const eventsList = document.getElementById('events-list');
        eventsList.innerHTML = '';

        if (pets.length === 0) {
            eventsList.innerHTML = "<li>No events to display.</li>";
            return;
        }

        // collect all events from all pets
        const allEvents = [];
        for (const pet of pets) {
            const eventsResponse = await fetch(`/api/pets/${pet.id}/events`);
            if (!eventsResponse.ok) continue;

            const petEvents = await eventsResponse.json();
            petEvents.forEach(e => e.pet = pet); // Attach pet info for display
            allEvents.push(...petEvents);
        }

        // Sort events by start time
        allEvents.sort((a, b) => new Date(a.eventStartTime) - new Date(b.eventStartTime));

        allEvents.forEach(event => {
            const li = document.createElement('li');
            const start = new Date(event.eventStartTime);
            const end = new Date(event.eventEndTime);
            li.textContent = `${event.eventName} — ${formatDateTime(start)} to ${formatDateTime(end)} (Pet: ${event.pet.petName})`;
            eventsList.appendChild(li);
        });

    } catch (error) {
        console.error(error);
        document.getElementById('events-list').innerHTML = "<li>Error loading events</li>";
    }
}




/* Add  news event
 */
async function addEvent(event) {
    event.preventDefault();

    const name = document.getElementById('eventName').value;
    const start = document.getElementById('eventStart').value;
    const end = document.getElementById('eventEnd').value;
    const id = document.getElementById('eventPetSelect').value;

    const eventData = { eventName: name, eventStartTime: start, eventEndTime: end };

    try {
        const response = await fetch(`/api/pets/${id}/events`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(eventData)
        });

        if (!response.ok) throw new Error("Failed to add event");

        loadEvents(); // refresh events list
        document.getElementById('add-event-form').reset();
    } catch (error) {
        console.error(error);
    }
}

/* fils dropdown for selecting pets when adding events
 */
function fillPetSelectForEvents(pets) {
    const select = document.getElementById('eventPetSelect');

    fetch('/api/pets')
        .then(response => response.json())
        .then(pets => {
            select.innerHTML = ''; // clear existing options
            pets.forEach(pet => {
                const option = document.createElement('option');
                option.value = pet.id;
                option.textContent = pet.petName;
                select.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching pets:', error));
}

/* fills the dropdown with events for deleting
 */
async function fillDeleteEventDropdown() {
    const selector = document.getElementById('delete-event-selector');
    selector.innerHTML = '<option value="">Select an event...</option>';

    try {
        const petsResponse = await fetch('/api/pets');
        if (!petsResponse.ok) throw new Error("Failed to load pets");

        const pets = await petsResponse.json();

        for (const pet of pets) {
            const eventsResponse = await fetch(`/api/pets/${pet.id}/events`);
            if (!eventsResponse.ok) continue;

            const events = await eventsResponse.json();

            events.forEach(event => {
                const option = document.createElement("option");
                option.value = `${pet.id}:${event.id}`;
                option.textContent =
                    `${event.eventName} — ${event.eventStartTime} (Pet: ${pet.petName})`;
                selector.appendChild(option);
            });
        }

    } catch (err) {
        console.error("Error loading delete dropdown:", err);
    }
}

/* Delete selected event
 */
async function deleteSelectedEvent() {
    const selector = document.getElementById('delete-event-selector');
    const value = selector.value;

    if (!value) {
        alert("Please select an event to delete.");
        return;
    }

    const [id, eventId] = value.split(":");

    if (!confirm("Are you sure you want to delete this event?")) return;

    try {
        const response = await fetch(`/api/pets/${id}/events/${eventId}`, {
            method: "DELETE"
        });

        if (!response.ok) throw new Error("Failed to delete event");

        alert("Event deleted!");

        loadEvents();
        fillDeleteEventDropdown();
    } catch (err) {
        console.error(err);
    }
}



/* Call this on page load
 */
document.addEventListener('DOMContentLoaded', () => {
    populateEventPetSelect();
});

/* Format DateTime for display
 */
function formatDateTime(dt) {
    return `${dt.getMonth() + 1}/${dt.getDate()}/${dt.getFullYear()} ${dt.getHours()}:${dt.getMinutes().toString().padStart(2, '0')}`;
}




// Initial load
loadPets();
loadEvents();
fillDeleteEventDropdown();

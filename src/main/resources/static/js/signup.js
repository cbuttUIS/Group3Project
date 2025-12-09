document.addEventListener('DOMContentLoaded', () => {
    const signupForm = document.getElementById('signupForm');
    if (!signupForm) return;

    signupForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('passwordcon').value;

        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        const data = { username, email, password };

        try {
            const response = await fetch('/signup', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                alert("Signup successful! Redirecting to login...");
                window.location.href = "/login";
            } else {
                const errorText = await response.text();
                alert("Signup failed: " + errorText);
            }
        } catch (error) {
            console.error("Error during signup:", error);
            alert("Signup error, check console.");
        }
    });

    // fade in animation, can be removed
    signupForm.style.opacity = 0;
    setTimeout(() => {
        signupForm.style.transition = 'opacity 1s ease-in-out';
        signupForm.style.opacity = 1;
    }, 500);
});


document.addEventListener("DOMContentLoaded", () => {
    // Gets the login and signup buttons
    const loginButton = document.getElementById("loginButton");
    const signupButton = document.getElementById("signupButton");

    // login button event listener
    if (loginButton) {
        loginButton.addEventListener("click", () => {
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            // Validation login
            if (email && password) {
                // redirect to homepage.html if login details are valid
                window.location.href = "homepage.html";
            } else {
                alert("Please enter a valid email and password.");
            }
        });
    }

    // signup button event listener
    if (signupButton) {
        signupButton.addEventListener("click", () => {
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;

            // validation sign up
            if (email && password && confirmPassword) {
                if (password === confirmPassword) {
                    // redirect to login page when signup details are valid
                    window.location.href = "index.html";
                } else {
                    alert("Passwords do not match. Please try again.");
                }
            } else {
                alert("Please fill in all fields.");
            }
        });
    }
});

whenDomReady(() => {
    const signinForm = document.querySelector('#signin');
    const signinLoader = signinForm.querySelector('.loader');

    const usernameInput = document.querySelector('#username');
    const passwordInput = document.querySelector('#password');

    const errors = document.querySelectorAll('.error');

    let errorsOff = false;

    const removeErrors = () => {
        if (errorsOff) {
            return;
        }

        for (let error of errors) {
            error.style.display = 'none';
        }

        usernameInput.classList.remove('border-red-500');
        passwordInput.classList.remove('border-red-500');

        errorsOff = true;
    };

    usernameInput.oninput = removeErrors;
    passwordInput.oninput = removeErrors;

    signinForm.onsubmit = (event) => {
        event.preventDefault();

        signinLoader.style.display = 'inline';
        ajax(new FormData(signinForm), signinForm.action);
    };
});
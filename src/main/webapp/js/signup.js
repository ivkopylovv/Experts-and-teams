whenDomReady(() => {
    console.log('signup');
    const signupForm = document.querySelector('#signup');

    signupForm.onsubmit = (event) => {
        event.preventDefault();

        ajax(new FormData(signupForm), signupForm.action);
    };
});
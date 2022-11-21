whenDomReady(() => {
    console.log('signin');
    const signinForm = document.querySelector('#signin');

    signinForm.onsubmit = (event) => {
        event.preventDefault();

        ajax(new FormData(signinForm), signinForm.action);
    };
});
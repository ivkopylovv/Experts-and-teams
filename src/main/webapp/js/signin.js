whenDomReady(() => {
    const signinForm = document.querySelector('#signin');
    const signinLoader = signinForm.querySelector('.loader');

    const usernameControl = new Control(
        document.querySelector('#username'), (control) => control.value.trim() !== ''
    );
    const passwordControl = new Control(
        document.querySelector('#password'), (control) => control.value.trim() !== ''
    );

    usernameControl.node.oninput = usernameControl.offError.bind(usernameControl);
    passwordControl.node.oninput = passwordControl.offError.bind(passwordControl);

    signinForm.onsubmit = (event) => {
        event.preventDefault();
        signinLoader.style.display = 'inline';

        const usernameControlValid = usernameControl.validate();
        const passwordControlValid = passwordControl.validate();


        if (!usernameControlValid) {
            usernameControl.showError();
        }

        if (!passwordControlValid) {
            passwordControl.showError();
        }

        const formValid = usernameControlValid && passwordControlValid;

        if (!formValid) {
            signinLoader.style.display = 'none';
            return;
        }

        ajax(new FormData(signinForm), signinForm.action);
    };
});
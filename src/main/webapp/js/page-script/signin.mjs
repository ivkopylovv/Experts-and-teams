import {whenDomReady, submitForm} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {FormGroup} from '../entity/formGroup.mjs';

const SigninControl = {
    Username: 'username',
    Password: 'password'
};

function handleSigninSubmit() {
    const formData = new FormData(this.getFormElement());

    submitForm(formData, this.getAction());
}

whenDomReady(() => {
    const controls = {
        [SigninControl.Username]: new Control('#username', [Validators.required]),
        [SigninControl.Password]: new Control('#password', [Validators.required]),
    };

    const formGroup = new FormGroup(
        '#signin',
        '.loader',
        controls,
        handleSigninSubmit
    );
});
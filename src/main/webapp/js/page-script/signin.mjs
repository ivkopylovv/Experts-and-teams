import {whenDomReady, makeRequest} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';

const SigninControl = {
    USERNAME: 'username',
    PASSWORD: 'password'
};
const ACTION = 'signin';

function handleSigninSubmit() {
    const formData = new FormData(this.getFormElement());

    makeRequest(ACTION, {body: formData, method: 'post'}).then(() => {
        // TODO:
    });
}

function main() {
    const controls = {
        [SigninControl.USERNAME]: new Control('#username', [Validators.required]),
        [SigninControl.PASSWORD]: new Control('#password', [Validators.required]),
    };

    const formGroup = new FormGroup(
        createFormConfig('#signin-form', '.loader'),
        controls,
        handleSigninSubmit
    );
}

const SIGNIN = '#signin';

whenDomReady(() => {
    const isSignin = document.querySelector(SIGNIN);

    if (!isSignin) {
        return;
    }

    main();
});
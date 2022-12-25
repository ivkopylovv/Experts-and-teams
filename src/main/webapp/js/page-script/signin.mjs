import {makeRequest, redirect, whenDomReady} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {Route} from "../const/route.mjs";

const SigninControl = {
    USERNAME: 'username',
    PASSWORD: 'password'
};

function handleSigninSubmit() {
    const usernameControl = this.getControl(SigninControl.USERNAME);
    const passwordControl = this.getControl(SigninControl.PASSWORD);

    const dto = {
        username: usernameControl.getValue(),
        password: passwordControl.getValue()
    };

    makeRequest(Route.SIGNIN, {body: dto, method: 'post'}, false).then(res => {
        if (res.status === 400) {
            usernameControl.showError();
            passwordControl.showError();

            return;
        }

        redirect(res.data.url);
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

const isSignin = document.querySelector('#signin');

if (isSignin) {
    main();
}
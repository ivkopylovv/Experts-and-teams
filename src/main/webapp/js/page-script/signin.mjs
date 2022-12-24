import {whenDomReady, makeRequest, redirect} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {Route} from "../const/route.mjs";

const SigninControl = {
    USERNAME: 'username',
    PASSWORD: 'password'
};

function handleSigninSubmit() {
    const username = this.getControl(SigninControl.USERNAME).getValue();
    const password = this.getControl(SigninControl.PASSWORD).getValue();

    const dto = {
        username,
        password
    };

    makeRequest(Route.SIGNIN, {body:dto, method: 'post'}, false).then(res => {
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

const SIGNIN = '#signin';

whenDomReady(() => {
    const isSignin = document.querySelector(SIGNIN);

    if (!isSignin) {
        return;
    }

    main();
});
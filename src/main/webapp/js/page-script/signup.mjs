import {ROLE} from '../const/global.mjs';
import {whenDomReady, makeRequest, sendNotification, redirect} from '../util.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {handleExpertSkills} from '../shared/skills.mjs';
import {Route} from "../const/route.mjs";

const SignupControl = {
    NAME: 'name',
    USERNAME: 'username',
    PASSWORD: 'password',
    CONFIRM_PASSWORD: 'confirmPassword'
};

const Role = {
    EXPERT: 'Expert',
    USER: 'User'
};

function handleSignupSubmit() {
    const formElement = this.getFormElement();
    const skills = [...formElement.querySelectorAll('input[name="skill"]')]
        .map(input => input.value?.trim() ?? '')
        .filter(value => !!value);

    const dto = {
        name: this.getControl(SignupControl.NAME).getValue(),
        username: this.getControl(SignupControl.USERNAME).getValue(),
        password: this.getControl(SignupControl.PASSWORD).getValue(),
        role: !!skills.length ? Role.EXPERT : Role.USER,
        skills
    };

    makeRequest(Route.SIGNUP, {body: dto, method: 'post'}, false).then(res => {
        if (res?.status === 400) {
            sendNotification('Error', res.data.message);
            this.clear();

            return;
        }

        redirect(Route.SIGNIN);
    });
}

function passwordValidator() {
    const passwordControl = this.getControl(SignupControl.PASSWORD);
    const confirmPasswordControl = this.getControl(SignupControl.CONFIRM_PASSWORD);

    return passwordControl.getValue() === confirmPasswordControl.getValue();
}

function handleSignup() {
    const controls = {
        [SignupControl.NAME]: new Control('#name', [Validators.required]),
        [SignupControl.USERNAME]: new Control('#username', [Validators.required]),
        [SignupControl.PASSWORD]: new Control('#password', [Validators.required]),
        [SignupControl.CONFIRM_PASSWORD]: new Control('#confirmPassword', [Validators.required])
    };

    const formGroup = new FormGroup(
        createFormConfig('#signup-form'),
        controls,
        handleSignupSubmit,
        [passwordValidator]
    );

    const skillsContainer = handleExpertSkills();
    const checkbox = SelectorEngine.findOne('#isExpert');

    checkbox.addEventListener('click', () => {
        if (checkbox.checked) {
            skillsContainer.style.display = 'block';
        } else {
            skillsContainer.style.display = 'none';
        }
    });
}

const isSignup = !!document.querySelector('#signup');

if (isSignup) {
    handleSignup();
}

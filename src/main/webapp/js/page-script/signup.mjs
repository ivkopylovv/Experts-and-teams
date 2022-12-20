import {ROLE} from '../const/global.mjs';
import {whenDomReady, makeRequest} from '../util.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {handleExpertSkills} from '../shared/skills.mjs';
import {handleAlerts} from '../shared/alert.mjs';

const SignupControl = {
    NAME: 'name',
    USERNAME: 'username',
    PASSWORD: 'password',
    CONFIRM_PASSWORD: 'confirmPassword'
};

const Role = {
    EXPERT: 'expert',
    USER: 'user'
};

function handleSignupSubmit() {
    const formData = new FormData(this.getFormElement());
    const thereIsSkill = checkThereIsSkill(formData);

    formData.set(ROLE, thereIsSkill ? Role.EXPERT : Role.USER);

    makeRequest(this.getAction(), {body: formData}).then(() => {
        // TODO:
    });
}

function passwordValidator() {
    const passwordControl = this.getControl(SignupControl.Password);
    const confirmPasswordControl = this.getControl(SignupControl.ConfirmPassword);

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
        createFormConfig('#signup'),
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

function checkThereIsSkill(formData) {
    for (const pair of formData.entries()) {
        const key = pair[0];
        const value = pair[1];

        if (key === 'skill' && typeof value === 'string' && value !== '') {
            return true;
        }
    }

    return false;
}

const SIGNUP = '#signup';

whenDomReady(() => {
    const isSignup = !!document.querySelector(SIGNUP);

    if (!isSignup) {
        return;
    }

    handleSignup();
});

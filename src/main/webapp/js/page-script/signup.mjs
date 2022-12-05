import {whenDomReady, submitForm} from '../util.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {handleExpertSkills} from '../shared/skills.mjs';

const SignupControl = {
    Name: 'name',
    Username: 'username',
    Password: 'password',
    ConfirmPassword: 'confirmPassword'
};

const ROLE = 'role';

const Role = {
    Expert: 'expert',
    User: 'user'
};

function handleSignupSubmit() {
    const formData = new FormData(this.getFormElement());
    const thereIsSkill = checkThereIsSkill(formData);

    formData.set(ROLE, thereIsSkill ? Role.Expert : Role.User);

    submitForm(formData, this.getAction());
}

function passwordValidator() {
    const passwordControl = this.getControl(SignupControl.Password);
    const confirmPasswordControl = this.getControl(SignupControl.ConfirmPassword);

    return passwordControl.getValue() === confirmPasswordControl.getValue();
}

whenDomReady(() => {
    const controls = {
        [SignupControl.Name]: new Control('#name', [Validators.required]),
        [SignupControl.Username]: new Control('#username', [Validators.required]),
        [SignupControl.Password]: new Control('#password', [Validators.required]),
        [SignupControl.ConfirmPassword]: new Control('#confirmPassword', [Validators.required])
    };

    const formGroup = new FormGroup(
        createFormConfig('#signup', '.loader'),
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
});

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

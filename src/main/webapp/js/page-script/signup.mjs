whenDomReady(() => {
    const signupForm = document.querySelector('#signup');
    const signupLoader = signupForm.querySelector('.loader');

    const nameControl = new Control(
        document.querySelector('#name'),
        (node) => (node.value?.trim() ?? '') !== ''
    );
    const usernameControl = new Control(
        document.querySelector('#username'),
        (node) => (node.value?.trim() ?? '') !== ''
    );
    const passwordControl = new Control(
        document.querySelector('#password')
    );
    const confirmPasswordControl = new Control(
        document.querySelector('#confirmPassword')
    );

    nameControl.node.oninput =
        nameControl.offError.bind(nameControl);
    usernameControl.node.oninput =
        usernameControl.offError.bind(usernameControl);
    passwordControl.node.oninput = () => {
        passwordControl.offError.call(passwordControl);
        confirmPasswordControl.offError.call(confirmPasswordControl);
    };
    confirmPasswordControl.node.oninput = () => {
        passwordControl.offError.call(passwordControl);
        confirmPasswordControl.offError.call(confirmPasswordControl);
    };

    signupForm.onsubmit = (event) => {
        event.preventDefault();
        signupLoader.style.display = 'inline';

        const password = passwordControl.getValue();
        const confirmPassword = confirmPasswordControl.getValue();

        const nameValid = nameControl.validate();
        const usernameValid = usernameControl.validate();
        const passwordValid = password === confirmPassword && password !== '';

        const formValid = nameValid && usernameValid && passwordValid;

        if (!nameValid) {
            nameControl.showError();
        }

        if (!usernameValid) {
            usernameControl.showError();
        }

        if (!passwordValid) {
            passwordControl.showError();
            confirmPasswordControl.showError();
        }

        if (!formValid) {
            signupLoader.style.display = 'none';
            return;
        }

        const formData = new FormData(signupForm);

        submitForm(signupMapper(formData), signupForm.action);
    };

    handleExpertSkills();
});

function signupMapper(formData) {
    const params = new URLSearchParams();

    for (const pair of formData.entries()) {
        const key = pair[0];
        const value = pair[1];

        if (typeof value === 'string' && value !== '') {
            params.append(key, value);
        }
    }

    return params.toString();
}

// TODO: added validation of skills
function handleExpertSkills() {
    const checkbox = document.querySelector('#is-expert');
    const skills = document.querySelector('#skills');
    const skillsContainer = document.querySelector('#skills-container');
    const template = document.querySelector('#skill-template');
    const addSkillBtn = document.querySelector('#add-skill');

    const createSkill = () => {
        const el = document.importNode(template.content.querySelector('div'), true);

        const removeBtn = el.querySelector('.delete');

        skillsContainer.appendChild(el);

        removeBtn.addEventListener('click', () => {
            skillsContainer.removeChild(el);
        });
    };

    const handleRemoveButtonDisabling = () => {
        let disabledBtn = null;

        const observer = new MutationObserver(() => {
            if (skillsContainer.childNodes.length === 1) {
                const removeBtn = skillsContainer.childNodes[0].querySelector('.delete');

                disabledBtn = removeBtn;
                removeBtn.disabled = true;

                return;
            }

            if (skillsContainer.childNodes.length === 8) {
                addSkillBtn.style.display = 'none';
            } else {
                addSkillBtn.style.display = 'block';
            }

            if (disabledBtn) {
                disabledBtn.disabled = false;
                disabledBtn = null;
            }
        });

        observer.observe(skillsContainer, {childList: true});
    };

    handleRemoveButtonDisabling();
    createSkill();

    addSkillBtn.addEventListener('click', createSkill);

    checkbox.addEventListener('click', () => {
        if (checkbox.checked) {
            skills.style.display = 'block';
        } else {
            skills.style.display = 'none';
        }
    });
}
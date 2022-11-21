whenDomReady(() => {
    const signupForm = document.querySelector('#signup');

    signupForm.onsubmit = (event) => {
        event.preventDefault();

        ajax(new FormData(signupForm), signupForm.action);
    };

    handleExpertSkills();
});

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
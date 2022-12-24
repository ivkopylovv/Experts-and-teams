import {SelectorEngine} from '../dom/selector-engine.mjs';

// TODO: added validation of skills
export function handleExpertSkills() {
    const skills = SelectorEngine.findOne('#skills');
    const skillsContainerElement = SelectorEngine.findOne('#skills-container');
    const addSkillBtnElement = SelectorEngine.findOne('#add-skill');

    const createSkill = () => {
        const skillElement = SelectorEngine.importElement('#skill-template');
        skillsContainerElement.appendChild(skillElement);

        const index = skillsContainerElement.children.length - 1;
        const appendedElement = skillsContainerElement.children[index];

        appendedElement.querySelector('.delete').addEventListener('click', () => {
            appendedElement.remove();
        });
    };

    const handleRemoveButtonDisabling = () => {
        let disabledBtn = null;

        const observer = new MutationObserver(() => {
            if (skillsContainerElement.children.length === 1) {
                const removeBtn = SelectorEngine.findOne('.delete', skillsContainerElement.children[0]);

                disabledBtn = removeBtn;
                removeBtn.disabled = true;

                return;
            }

            if (skillsContainerElement.children.length === 8) {
                addSkillBtnElement.style.display = 'none';
            } else {
                addSkillBtnElement.style.display = 'block';
            }

            if (disabledBtn) {
                disabledBtn.disabled = false;
                disabledBtn = null;
            }
        });

        observer.observe(skillsContainerElement, {childList: true});
    };

    handleRemoveButtonDisabling();
    createSkill();

    addSkillBtnElement.addEventListener('click', createSkill);

    return skills;
}

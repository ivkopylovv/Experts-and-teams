import {SelectorEngine} from '../dom/selector-engine.mjs';

// TODO: added validation of skills
export function handleExpertSkills() {
    const skills = SelectorEngine.findOne('#skills');
    const skillsContainerElement = SelectorEngine.findOne('#skills-container');
    const addSkillBtnElement = SelectorEngine.findOne('#add-skill');

    const createSkill = () => {
        const skillElement = SelectorEngine.importElement('#skill-template');
        const removeBtnElement = SelectorEngine.findOne('.delete', skillElement);

        skillsContainerElement.appendChild(skillElement);

        removeBtnElement.addEventListener('click', () => {
            skillsContainerElement.removeChild(skillElement);
        });
    };

    const handleRemoveButtonDisabling = () => {
        let disabledBtn = null;

        const observer = new MutationObserver(() => {
            if (skillsContainerElement.childNodes.length === 1) {
                const removeBtn = SelectorEngine.findOne('.delete', skillsContainerElement.childNodes[0]);

                disabledBtn = removeBtn;
                removeBtn.disabled = true;

                return;
            }

            if (skillsContainerElement.childNodes.length === 8) {
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

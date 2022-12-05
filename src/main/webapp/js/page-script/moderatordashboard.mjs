import {SelectorEngine} from '../dom/selector-engine.mjs';
import {submitForm, whenDomReady} from '../util.mjs';

const HIDDEN = 'hidden';
const ACTION = 'moderatordashboard';

function handleChanges() {
    const changesAlertElement = SelectorEngine.findOne('#changes-alert');
    const changesCountElement = SelectorEngine.findOne('#changes-count');
    const applyChangesBtnElement = SelectorEngine.findOne('#apply-changes');

    const tableRowElements = SelectorEngine.find('.table-row');
    const initialState = [...tableRowElements].map(rowElement => {
        const userId = SelectorEngine.findOne('.id', rowElement).innerText.trim();
        const isBlocked = SelectorEngine.findOne('.block-checkbox', rowElement).checked;

        return {
            userId,
            isBlocked
        };
    });
    const state = initialState.map(rowState => ({...rowState}));
    const blockCheckboxElements = SelectorEngine.find('.block-checkbox');

    let changes = 0;

    [...blockCheckboxElements].forEach((checkboxElement, index) => {
        checkboxElement.onclick = () => {
            state[index].isBlocked = checkboxElement.checked;

            const changesCount = state.reduce(
                (acc, value, index) =>
                    value.isBlocked === initialState[index].isBlocked ? acc : acc + 1,
                0
            );

            changes = changesCount;

            if (changesCount === 0) {
                if (!changesAlertElement.classList.contains(HIDDEN)) {
                    changesAlertElement.classList.add(HIDDEN);
                }
            } else {
                if (changesAlertElement.classList.contains(HIDDEN)) {
                    changesAlertElement.classList.remove(HIDDEN);
                }
            }

            changesCountElement.textContent = changesCount;
        };
    });

    applyChangesBtnElement.onclick = () => {
        if (changes === 0) {
            return;
        }

        const userIds = state
            .filter((rowState, index) =>
                rowState.isBlocked !== initialState[index].isBlocked
            )
            .map(rowState => rowState.userId.trim());

        const formData = new FormData();

        userIds.forEach(id => {
            formData.append('user_id', id);
        });

        submitForm(formData, ACTION, () => {
            handleChanges();
        });
    };
}

whenDomReady(() => {
    handleChanges();
});

import {HIDDEN_CLASS} from '../const/global.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';
import {makeRequest, redirect, reload, whenDomReady} from '../util.mjs';
import {Route} from "../const/route.mjs";

function main() {
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
                if (!changesAlertElement.classList.contains(HIDDEN_CLASS)) {
                    changesAlertElement.classList.add(HIDDEN_CLASS);
                }
            } else {
                if (changesAlertElement.classList.contains(HIDDEN_CLASS)) {
                    changesAlertElement.classList.remove(HIDDEN_CLASS);
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
            .map(rowState => +rowState.userId.trim());

        const dto = {userIds};

        makeRequest(Route.MODERATOR_DASHBOARD, {body: dto, method: 'post'}).then(() => {
            reload();
        });
    };
}

const MODERATOR_DASHBOARD = '#moderator-dashboard';

whenDomReady(() => {
    const isModeratorDashboard = !!document.querySelector(MODERATOR_DASHBOARD);

    if (!isModeratorDashboard) {
        return;
    }

    main();
});

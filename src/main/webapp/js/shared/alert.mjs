import {SelectorEngine} from '../dom/selector-engine.mjs';
import {HIDDEN_CLASS} from '../const/global.mjs';

export function handleAlerts() {
    const alertCloseBtnElements = SelectorEngine.find('.close-alert-btn');

    [...alertCloseBtnElements].forEach(alertCloseBtnElement => {
        const alertElement = SelectorEngine.parents(
            alertCloseBtnElement,
            alertCloseBtnElement.getAttribute('data-dismiss-target')
        )[0];

        alertCloseBtnElement.onclick = () => {
            alertElement.classList.add(HIDDEN_CLASS);
        };
    });
}
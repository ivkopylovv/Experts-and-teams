import {SelectorEngine} from '../dom/selector-engine.mjs';

const HIDDEN = 'hidden';

export function handleAlerts() {
    const alertCloseBtnElements = SelectorEngine.find('.close-alert-btn');

    [...alertCloseBtnElements].forEach(alertCloseBtnElement => {
        const alertElement = SelectorEngine.parents(
            alertCloseBtnElement,
            alertCloseBtnElement.getAttribute('data-dismiss-target')
        )[0];

        alertCloseBtnElement.onclick = () => {
            alertElement.classList.add(HIDDEN);
        };
    });
}
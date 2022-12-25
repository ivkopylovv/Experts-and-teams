import {SelectorEngine} from "../dom/selector-engine.mjs";
import {makeRequest} from "../util.mjs";
import {Route} from "../const/route.mjs";

export function handleNotifications() {
    const notificationBtnEl = document.querySelector('#notification-btn');
    const notificationContainerEl = document.querySelector('#notification-container');

    const createNotificationEl = ({id, message, date, isAccepted}) => {
        const notificationTemplateEl = SelectorEngine.importElement('#notification-template');

        const notificationEl = notificationTemplateEl.querySelector('.notification');

        if (isAccepted) {
            notificationEl.classList.add('bg-green-100', 'hover:bg-green-200');
        } else {
            notificationEl.classList.add('bg-red-100', 'hover:bg-red-200');
        }

        notificationTemplateEl.querySelector('.message').innerHTML = message;
        notificationTemplateEl.querySelector('.date').innerHTML = date;

        const removeBtnEl = notificationTemplateEl.querySelector('.remove-btn');

        removeBtnEl.setAttribute('data-notification-id', id);
        removeBtnEl.addEventListener('click', async () => {
            const query = {notification_id: id};

            await makeRequest(Route.NOTIFICATIONS, {method: 'post', query});

            notificationContainerEl.querySelector(`[data-notification-id="${id}"]`).parentElement.remove();
        });

        notificationContainerEl.appendChild(notificationTemplateEl);
    };

    notificationBtnEl.addEventListener('click', async () => {
        const notifications = (await makeRequest(Route.NOTIFICATIONS, {}, false)).data;

        notificationContainerEl.innerHTML = '';

        if (!!notifications.length) {
            notifications.forEach(notification => createNotificationEl(notification));
        } else {
            const emptyNotificationEl = SelectorEngine.importElement('#empty-notification-template');

            notificationContainerEl.appendChild(emptyNotificationEl);
        }
    });
}
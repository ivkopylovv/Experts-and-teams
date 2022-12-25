import {SelectorEngine} from "../dom/selector-engine.mjs";
import {makeRequest} from "../util.mjs";
import {Route} from "../const/route.mjs";

export function handleNotifications() {
    const notificationBtnEl = document.querySelector('#notification-btn');
    const notificationContainerEl = document.querySelector('#notification-container');

    const createNotificationEl = ({id, message, date}) => {
        const notificationEl = SelectorEngine.importElement('#notification-template');

        notificationEl.querySelector('.message').innerHTML = message;
        notificationEl.querySelector('.date').innerHTML = date;

        const removeBtnEl = notificationEl.querySelector('.remove-btn');

        removeBtnEl.setAttribute('data-notification-id', id);
        removeBtnEl.addEventListener('click', async () => {
            const query = {notification_id: id};

            await makeRequest(Route.NOTIFICATIONS, {method: 'post', query});

            notificationContainerEl.querySelector(`[data-notification-id="${id}"]`).parentElement.remove();
        });

        notificationContainerEl.appendChild(notificationEl);
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
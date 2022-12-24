import {Control, Validators} from "../entity/control.mjs";
import {makeRequest} from "../util.mjs";
import {Route} from "../const/route.mjs";
import {SelectorEngine} from "../dom/selector-engine.mjs";

const isTeamChat = document.querySelector('#team-chat');
const params = new Proxy(new URLSearchParams(window.location.search), {
    get: (searchParams, prop) => searchParams.get(prop),
});

if (isTeamChat) {
    main();
}

function main() {
    sendingMessage();
    joinRequests();
    lastMessages();
}

function sendingMessage() {
    const messageControl = new Control('#chat-message-input', [Validators.required]);
    const sendMessageBtnElement = document.querySelector('#send-message-btn');

    const sendMessage = async expertId => {
        if (!messageControl.validate()) {
            messageControl.showError();

            return;
        }

        const dto = {
            message: messageControl.getValue(),
            teamId: +params.team_id,
            expertId
        };

        await makeRequest(Route.TEAM_CHAT, {method: 'post', body: dto});

        messageControl.clear();
    };

    sendMessageBtnElement.addEventListener('click', async () => {
        await sendMessage();
    });
}

function createMessage({userName, expertName, message, date}, container) {
    const messageElement = SelectorEngine.importElement('#message-template');

    if (expertName) {
        messageElement.querySelector('.message-user-name').innerHTML = `${userName} to ${expertName}`;
    } else {
        messageElement.querySelector('.message-user-name').innerHTML = userName;
    }

    messageElement.querySelector('.message-body').innerHTML = message;
    messageElement.querySelector('.message-date').innerHTML = date;

    container.appendChild(messageElement);
}

function lastMessages() {
    const messageContainerEl = document.querySelector('#message-container');

    setInterval(async () => {
        const query = {team_id: +params.team_id}

        const res = await makeRequest(Route.TEAMS_CHAT_LAST_MESSAGES, {query}, false);

        res.data.forEach(message => createMessage(message, messageContainerEl));
    }, 5000);
}

function joinRequests() {
    const settingsBtnElement = document.querySelector('#settings-btn');
    const joinRequestsContainerElement = document.querySelector('#join-requests-container');

    const makeDecisionOnRequest = async (id, isAccepted) => {
        const joinRequestEl = joinRequestsContainerElement.querySelector(`tr[data-request-id="${id}"]`);

        const dto = {id, isAccepted};

        await makeRequest(Route.TEAMS_JOIN_REQUEST_DECISION, {body: dto, method: 'post'});
        joinRequestsContainerElement.removeChild(joinRequestEl);
    };

    const createJoinRequestElement = ({id, userName, message}) => {
        const joinRequestElement = SelectorEngine.importElement('#join-request-template');

        joinRequestElement.querySelector('.table-row').setAttribute('data-request-id', id);
        joinRequestElement.querySelector('.request-id').innerHTML = id;
        joinRequestElement.querySelector('.request-user-name').innerHTML = userName;
        joinRequestElement.querySelector('.request-message').innerHTML = message;

        joinRequestElement.querySelector('.accept').addEventListener('click', async () => {
            await makeDecisionOnRequest(id, true);
        });

        joinRequestElement.querySelector('.cancel').addEventListener('click', async () => {
            await makeDecisionOnRequest(id, false);
        });

        joinRequestsContainerElement.appendChild(joinRequestElement);
    };

    settingsBtnElement?.addEventListener('click', async () => {
        const res = await makeRequest(Route.TEAMS_JOIN_REQUEST, {}, false);

        joinRequestsContainerElement.innerHTML = '';

        res.data.forEach(dto => createJoinRequestElement(dto));
    })
}
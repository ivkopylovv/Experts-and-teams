import {Control, Validators} from "../entity/control.mjs";
import {makeRequest} from "../util.mjs";
import {Route} from "../const/route.mjs";
import {SelectorEngine} from "../dom/selector-engine.mjs";
import {handleNotifications} from "../shared/notification.mjs";

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
    teamExperts();
    availableExperts();
    handleNotifications();
}

function availableExperts() {
    const writeToExpertBtnEl = document.querySelector('#write-to-expert-btn');
    const availableExpertContainerEl = document.querySelector('#write-expert-container');
    const messageContainerEl = document.querySelector('#message-container');

    const messageControl = new Control('#expert-message-input', [Validators.required]);

    const sendMessage = async (expertId) => {
        if (!messageControl.validate()) {
            messageControl.showError();

            return;
        }

        const dto = {
            message: messageControl.getValue(),
            teamId: +params.team_id,
            expertId
        };

        const message = (await makeRequest(Route.TEAM_CHAT, {method: 'post', body: dto}, false)).data;

        createMessage(message, messageContainerEl);

        messageControl.clear();
        availableExpertContainerEl.querySelector(`tr[data-expert-id="${expertId}"]`).remove();
    };

    const createAvailableExpertEl = ({expertId, expertName, skills}) => {
        const availableExpertEl = SelectorEngine.importElement('#available-expert-template');

        availableExpertEl.querySelector('.table-row').setAttribute('data-expert-id', expertId);
        availableExpertEl.querySelector('.expert-name').innerHTML = expertName;
        availableExpertEl.querySelector('.expert-skills').innerHTML = skills.join(', ');


        availableExpertEl.querySelector('.send-btn').addEventListener('click', async () => {
            await sendMessage(expertId);
        });

        availableExpertContainerEl.appendChild(availableExpertEl);
    };

    writeToExpertBtnEl.addEventListener('click', async () => {
        const query = {team_id: params.team_id};
        const availableExperts = (await makeRequest(Route.TEAM_AVAILABLE_EXPERTS, {query}, false)).data;

        availableExpertContainerEl.innerHTML = '';

        availableExperts.forEach(availableExpert => createAvailableExpertEl(availableExpert));
    });
}

function teamExperts() {
    const settingsBtnElement = document.querySelector('#settings-btn');
    const teamExpertContainerEl = document.querySelector('#team-experts-container');

    const toggleBlock = async (expertId, blockStatus) => {
        const dto = {expertId, teamId: params.team_id, previousBlockStatus: blockStatus};

        await makeRequest(Route.TEAM_EXPERTS, {body: dto, method: 'post'});
    };

    const createTeamExpertElement = ({expertId, expertName, skills, isBlocked}) => {
        const teamExpertEl = SelectorEngine.importElement('#team-expert-template');

        teamExpertEl.querySelector('.block-expert').setAttribute('data-expert-id', expertId);
        teamExpertEl.querySelector('.expert-name').innerHTML = expertName;
        teamExpertEl.querySelector('.expert-skill').innerHTML = skills.join(', ');

        const blockExpertBtnEl = teamExpertEl.querySelector('.block-expert');

        blockExpertBtnEl.textContent = isBlocked ? 'Unblock' : 'Block';

        const toggleBtnTitle = () => {
            blockExpertBtnEl.textContent = blockExpertBtnEl.textContent === 'Block' ? 'Unblock' : 'Block';
        };

        blockExpertBtnEl.addEventListener('click', async () => {
            await toggleBlock(expertId, isBlocked);

            toggleBtnTitle();
        });

        teamExpertContainerEl.appendChild(teamExpertEl);
    };

    settingsBtnElement?.addEventListener('click', async () => {
        const query = {team_id: params.team_id};
        const res = await makeRequest(Route.TEAM_EXPERTS, {query}, false);

        teamExpertContainerEl.innerHTML = '';

        res.data.forEach(dto => createTeamExpertElement(dto));
    })

}

function sendingMessage() {
    const messageControl = new Control('#chat-message-input', [Validators.required]);
    const sendMessageBtnElement = document.querySelector('#send-message-btn');
    const messageContainerEl = document.querySelector('#message-container');

    const sendMessage = async () => {
        if (!messageControl.validate()) {
            messageControl.showError();

            return;
        }

        const dto = {
            message: messageControl.getValue(),
            teamId: +params.team_id
        };

        const message = (await makeRequest(Route.TEAM_CHAT, {method: 'post', body: dto}, false)).data;

        createMessage(message, messageContainerEl);

        messageControl.clear();
    };

    sendMessageBtnElement.addEventListener('click', async () => {
        await sendMessage();
    });
}

function createMessage({userName, expertName, message, date}, container, my = true) {
    const messageSelector = userName
        ? (my
            ? (expertName ? '#my-message-to-expert-template' : '#my-message-template')
            : (expertName ? '#other-message-to-expert-template' : '#other-message-template'))
        : '#common-message-template';
    const messageElement = SelectorEngine.importElement(messageSelector);

    if (expertName) {
        messageElement.querySelector('.message-user-name').innerHTML = `${userName} to ${expertName}`;
    } else {
        if (userName) {
            messageElement.querySelector('.message-user-name').innerHTML = userName;
        }
    }

    messageElement.querySelector('.message-body').innerHTML = message;

    if (userName) {
        messageElement.querySelector('.message-date').innerHTML = date;
    }

    container.appendChild(messageElement);
    container.scrollTop = container.scrollHeight;
}

function lastMessages() {
    const messageContainerEl = document.querySelector('#message-container');
    messageContainerEl.scrollTop = messageContainerEl.scrollHeight;

    setInterval(async () => {
        const query = {team_id: +params.team_id}

        const res = await makeRequest(Route.TEAMS_CHAT_LAST_MESSAGES, {query}, false);

        res.data.forEach(message => createMessage(message, messageContainerEl, false));
    }, 1500);
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
import {Control, Validators} from "../entity/control.mjs";
import {makeRequest} from "../util.mjs";
import {Route} from "../const/route.mjs";

const isTeamChat = document.querySelector('#team-chat');
const params = new Proxy(new URLSearchParams(window.location.search), {
    get: (searchParams, prop) => searchParams.get(prop),
});

if (isTeamChat) {
    main();
}

function main() {
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
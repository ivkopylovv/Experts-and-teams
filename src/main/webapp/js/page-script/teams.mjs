import {createFormConfig, FormGroup} from "../entity/formGroup.mjs";
import {Control, Validators} from "../entity/control.mjs";
import {makeRequest, redirect, reload, sendNotification} from "../util.mjs";
import {Route} from "../const/route.mjs";
import {SelectorEngine} from "../dom/selector-engine.mjs";
import {handleNotifications} from "../shared/notification.mjs";

const TEAM_NAME = 'name';

function handleNewTeam() {
    const query = {team_name: this.getControl(TEAM_NAME).getValue()};

    makeRequest(Route.TEAM_CREATE, {query, method: 'post'}).then(() => {
        reload();
    });
}

function main() {
    const controls = {
        [TEAM_NAME]: new Control('#create-team-name', [Validators.required])
    };

    const formGroup = new FormGroup(
        createFormConfig('#create-team-form'),
        controls,
        handleNewTeam
    );

    const leaveBtnElements = document.querySelectorAll('.leave-btn');

    let teamId = null;

    [...leaveBtnElements].forEach(leaveBtnElement => {
        leaveBtnElement.addEventListener('click', () => {
            teamId = +leaveBtnElement.getAttribute('data-team-id').trim();
        });
    });

    const confirmLeaveBtnElement = document.querySelector('#confirm-leave');

    confirmLeaveBtnElement.addEventListener('click', () => {
        const query = {team_id: teamId};

        makeRequest(Route.TEAM_LEAVE, {query, method: 'post'}, false).then(res => {
            if (res.status === 400) {
                sendNotification('Error', res.data.message);

                return;
            }

            reload();
        });
    });

    joinToTeam();
    openTeamChat();
    handleNotifications();
}

function openTeamChat() {
    const openChatBtnElements = document.querySelectorAll('.open-in-new');

    [...openChatBtnElements].forEach(openChatBtnElement => {
        openChatBtnElement.addEventListener('click', () => {
            const teamId = openChatBtnElement.getAttribute('data-team-id').trim();

            redirect(Route.TEAM_CHAT + `?team_id=${teamId}`);
        });
    });
}

function joinToTeam() {
    const joinToTeamBtnElement = SelectorEngine.findOne('#join-team-btn');
    const availableTeamContainerElement = SelectorEngine.findOne('#available-team-container');

    const joinMessageControl = new Control('#join-message', [Validators.required]);

    const sendMessage = async teamId => {
        if (!joinMessageControl.validate()) {
            joinMessageControl.showError();

            return;
        }

        const dto = {
            message: joinMessageControl.getValue(),
            teamId
        };

        await makeRequest(Route.TEAMS_JOIN_REQUEST, {method: 'post', body: dto});
        const teamToRemoveElement = availableTeamContainerElement.querySelector(`tr[data-team-id="${teamId}"]`);

        availableTeamContainerElement.removeChild(teamToRemoveElement);
        joinMessageControl.clear();
    };

    const createAvailableTeam = ({id, name, membersCount}) => {
        const availableTeamElement = SelectorEngine.importElement('#available-team-template');

        availableTeamElement.querySelector('.table-row').setAttribute('data-team-id', id);
        availableTeamElement.querySelector('.team-id').innerHTML = id;
        availableTeamElement.querySelector('.team-name').innerHTML = name;
        availableTeamElement.querySelector('.team-members-count').innerHTML = membersCount;

        availableTeamElement.querySelector('.join-btn').addEventListener('click', async () => {
            await sendMessage(id);
        });

        availableTeamContainerElement.appendChild(availableTeamElement);
    };

    joinToTeamBtnElement?.addEventListener('click', async () => {
        const res = await makeRequest(Route.TEAMS_AVAILABLE, {}, false);

        availableTeamContainerElement.innerHTML = '';

        res.data.forEach(dto => createAvailableTeam(dto));
    })
}

const isTeams = !!document.querySelector('#teams-dashboard');

if (isTeams) {
    main();
}

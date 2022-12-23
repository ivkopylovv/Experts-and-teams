import {createFormConfig, FormGroup} from "../entity/formGroup.mjs";
import {Control, Validators} from "../entity/control.mjs";
import {makeRequest, reload} from "../util.mjs";
import {Route} from "../const/route.mjs";

const TEAM_NAME = 'name';

function handleNewTeam() {
    const dto = {team_name: this.getControl(TEAM_NAME).getValue()};

    makeRequest(Route.TEAM_CREATE, {query: dto, method: 'post'}).then(() => {
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
}

const isTeams = !!document.querySelector('#teams-dashboard');

if (isTeams) {
    main();
}

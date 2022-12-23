import {Route} from '../const/route.mjs';
import {HIDDEN_CLASS} from '../const/global.mjs';
import {makeRequest, reload, whenDomReady} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {handleExpertSkills} from '../shared/skills.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';

const EditUserControl = {
    ID: 'id',
    NAME: 'name',
    USERNAME: 'username',
    PASSWORD: 'password',
    IS_BLOCKED: 'isBlocked'
};

const AddUserControl = {
    NAME: 'name',
    USERNAME: 'username',
    PASSWORD: 'password'
};

function handleEditUserSubmit() {
    const dto = {
        id: this.getControl(EditUserControl.ID).getValue(),
        name: this.getControl(EditUserControl.NAME).getValue(),
        username: this.getControl(EditUserControl.USERNAME).getValue(),
        password: this.getControl(EditUserControl.PASSWORD).getValue()
    };

    makeRequest(Route.ADMIN_DASHBOARD_EDIT_USER, {body: dto, method: 'post'}).then(() => {
        reload();
    });
}

function editUser() {
    const controls = {
        [EditUserControl.ID]: new Control('#editId', [Validators.required]),
        [EditUserControl.NAME]: new Control('#editName', [Validators.required]),
        [EditUserControl.USERNAME]: new Control('#editUsername', [Validators.required]),
        [EditUserControl.PASSWORD]: new Control('#editPassword', [Validators.required]),
        [EditUserControl.IS_BLOCKED]: new Control('#editIsBlocked', [Validators.required])
    };

    const formGroup = new FormGroup(
        createFormConfig('#edit-form'),
        controls,
        handleEditUserSubmit
    );

    const editUserBtns = SelectorEngine.find('.edit-btn');

    [...editUserBtns].forEach(editUserBtn => {
        editUserBtn.onclick = () => {
            const row = editUserBtn.closest('.table-row');

            const id = SelectorEngine.findOne('.id', row).innerText.trim();
            const name = SelectorEngine.findOne('.name', row).innerText.trim();
            const username = SelectorEngine.findOne('.username', row).innerText.trim();
            const password = SelectorEngine.findOne('.password').innerText.trim();
            const isBlocked = SelectorEngine.findOne('.isBlocked', row).innerText.trim();

            formGroup.getControl(EditUserControl.ID).setValue(id);
            formGroup.getControl(EditUserControl.NAME).setValue(name);
            formGroup.getControl(EditUserControl.USERNAME).setValue(username);
            formGroup.getControl(EditUserControl.PASSWORD).setValue(password);
            formGroup.getControl(EditUserControl.IS_BLOCKED).setValue(isBlocked);
        };
    });
}

function deleteUsers() {
    const deleteUsersBtnElements = SelectorEngine.find('.delete-btn');
    const confirmDeleteBtn = SelectorEngine.findOne('#confirm-delete');

    let userId = null;

    [...deleteUsersBtnElements].forEach(deleteUserBtnElement => {
        deleteUserBtnElement.addEventListener('click', () => {
            userId = +deleteUserBtnElement.getAttribute('data-user-id').trim();
        });
    });

    confirmDeleteBtn.onclick = () => {
        const dto = {userId};

        makeRequest(Route.ADMIN_DASHBOARD_DELETE_USER, {body: dto, method: 'post'}).then(() => {
            reload();
        });
    };
}

function handleAddUserSubmit() {
    const formElement = this.getFormElement();
    const skills = [...formElement.querySelectorAll('input[name="skill"]')]
        .map(input => input.value?.trim() ?? '')
        .filter(value => !!value);

    const dto = {
        name: this.getControl(AddUserControl.NAME).getValue(),
        username: this.getControl(AddUserControl.USERNAME).getValue(),
        password: this.getControl(AddUserControl.PASSWORD).getValue(),
        role: formElement.querySelector('input[name="role"]:checked'),
        skills
    };

    makeRequest(Route.ADMIN_DASHBOARD_ADD_USER, {body: dto, method: 'post'}).then(() => {
        reload();
    });
}

function addUser() {
    const controls = {
        [AddUserControl.Name]: new Control('#add-name', [Validators.required]),
        [AddUserControl.USERNAME]: new Control('#add-username', [Validators.required]),
        [AddUserControl.PASSWORD]: new Control('#add-password', [Validators.required]),
    };
    const formGroup = new FormGroup(
        createFormConfig('#add-form'),
        controls,
        handleAddUserSubmit
    );

    const skillsContainer = handleExpertSkills();
    const roleContainer = SelectorEngine.findOne('#role');

    roleContainer.onclick = () => {
        const role = SelectorEngine.findOne('input[name="role"]:checked').value;

        if (role === 'expert') {
            skillsContainer.style.display = 'block';
        } else {
            skillsContainer.style.display = 'none';
        }
    };
}

function handleSearch() {
    const searchElement = SelectorEngine.findOne('#table-search-users');
    const rows = SelectorEngine.find('.table-row');

    searchElement.oninput = (e) => {
        const value = e.target.value?.trim() ?? '';

        [...rows].forEach(row => {
            const name = SelectorEngine.findOne('.name', row).innerText.trim();
            const username = SelectorEngine.findOne('.username', row).innerText.trim();

            if (name.includes(value) || username.includes(value)) {
                if (row.classList.contains(HIDDEN_CLASS)) {
                    row.classList.remove(HIDDEN_CLASS);
                }
            } else {
                if (!row.classList.contains(HIDDEN_CLASS)) {
                    row.classList.add(HIDDEN_CLASS);
                }
            }
        });
    }
}

const ADMIN_DASHBOARD = '#admin-dashboard';

whenDomReady(() => {
    const isAdminDashboard = !!document.querySelector(ADMIN_DASHBOARD);

    if (!isAdminDashboard) {
        return;
    }

    // Edit user modal
    editUser();

    // Add user modal
    addUser();

    handleSearch();

    deleteUsers();
});
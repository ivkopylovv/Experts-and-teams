import {HIDDEN_CLASS, MODE} from '../const/global.mjs';
import {makeRequest, redirect, whenDomReady} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {handleExpertSkills} from '../shared/skills.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';

const ACTION = 'admin-dashboard';

const FormModes = {
    ADD_USER: 'add_user',
    UPDATE_USER: 'update_user',
    DELETE_USER: 'delete_users'
};

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
    const formData = new FormData(this.getFormElement());

    formData.set(MODE, FormModes.UPDATE_USER);

    makeRequest(ACTION, {body: formData, method: 'post'}).then(() => {
        redirect(ACTION);
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
        createFormConfig('#editForm'),
        controls,
        handleEditUserSubmit
    );

    const editUserBtns = SelectorEngine.find('.edit-btn');
    const closeEditModalBtn = SelectorEngine.findOne('#close-edit-modal');
    const editUserModal = SelectorEngine.findOne('#editUserModal');

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

            if (editUserModal.classList.contains(HIDDEN_CLASS)) {
                editUserModal.classList.remove(HIDDEN_CLASS);
            }
        };
    });

    closeEditModalBtn.onclick = () => {
        if (!editUserModal.classList.contains(HIDDEN_CLASS)) {
            editUserModal.classList.add(HIDDEN_CLASS);
        }
    };
}

function handleAddUserSubmit() {
    const formData = new FormData(this.getFormElement());

    formData.set(MODE, FormModes.ADD_USER);

    makeRequest(ACTION, {body: formData, method: 'post'}).then(() => {
        redirect(ACTION);
    });
}

function deleteUsers() {
    const selectAllCheckboxes = SelectorEngine.findOne('#select-all-checkboxes');

    selectAllCheckboxes.onclick = () => {
        const deleteUserCheckboxElements = SelectorEngine.find('.delete-user-checkbox');

        [...deleteUserCheckboxElements]
            .filter(checkbox => !checkbox.disabled)
            .forEach(checkbox => {
                checkbox.checked = selectAllCheckboxes.checked;
            });
    };

    const deleteUsersBtn = SelectorEngine.findOne('#deleteUsers');
    const confirmModalElement = SelectorEngine.findOne(
        deleteUsersBtn.getAttribute('data-modal-toggle')
    );
    const closeConfirmDeleteModalBtn = SelectorEngine.findOne('#close-confirm-delete-modal');
    const confirmDeleteBtn = SelectorEngine.findOne('#confirm-delete');
    const cancelDeleteBtn = SelectorEngine.findOne('#cancel-delete');

    const openConfirmModal = () => {
        if (confirmModalElement.classList.contains(HIDDEN_CLASS)) {
            confirmModalElement.classList.remove(HIDDEN_CLASS);
        }
    };
    const closeConfirmModal = () => {
        if (!confirmModalElement.classList.contains(HIDDEN_CLASS)) {
            confirmModalElement.classList.add(HIDDEN_CLASS);
        }
    };

    closeConfirmDeleteModalBtn.onclick = () => {
        closeConfirmModal();
    };
    cancelDeleteBtn.onclick = () => {
        closeConfirmModal();
    };
    deleteUsersBtn.onclick = () => {
        openConfirmModal();
    };

    confirmDeleteBtn.onclick = () => {
        const deleteUserCheckboxElements = SelectorEngine.find('.delete-user-checkbox');

        const userIds = [...deleteUserCheckboxElements]
            .filter(el => el.checked)
            .map(el => el.getAttribute('data-user-id')?.trim())
            .filter(userId => !!userId);

        if (userIds.length === 0) {
            return;
        }

        const formData = new FormData();

        userIds.forEach(id => {
            formData.append('user_id', id);
        });

        formData.set(MODE, FormModes.DELETE_USER);

        closeConfirmModal();
        makeRequest(ACTION, {body: formData, method: 'post'}).then(() => {
            redirect(ACTION);
        });
    };
}

function addUser() {
    const controls = {
        [AddUserControl.Name]: new Control('#addName', [Validators.required]),
        [AddUserControl.Username]: new Control('#addUsername', [Validators.required]),
        [AddUserControl.Password]: new Control('#addPassword', [Validators.required]),
    };
    const formGroup = new FormGroup(
        createFormConfig('#addForm'),
        controls,
        handleAddUserSubmit
    );

    const addUserBtn = SelectorEngine.findOne('#addUser');
    const addUserModal = SelectorEngine.findOne("#addUserModal");
    const closeAddUserModal = SelectorEngine.findOne('#close-add-modal');

    addUserBtn.onclick = () => {
        if (addUserModal.classList.contains(HIDDEN_CLASS)) {
            addUserModal.classList.remove(HIDDEN_CLASS);
        }
    };

    closeAddUserModal.onclick = () => {
        if (!addUserModal.classList.contains(HIDDEN_CLASS)) {
            addUserModal.classList.add(HIDDEN_CLASS);
        }
    };

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

function handleDropdownActionBtn() {
    const dropdownActionButtonElement = SelectorEngine.findOne('#actionDropdownBtn');
    const actionDropdownElement = SelectorEngine.findOne('#actionDropdown');

    dropdownActionButtonElement.onclick = () => {
        if (actionDropdownElement.classList.contains('hidden')) {
            actionDropdownElement.classList.remove('hidden');
        } else {
            actionDropdownElement.classList.add('hidden');
        }
    };

    document.addEventListener('click', ({target}) => {
        if (dropdownActionButtonElement.isEqualNode(target)) {
            return;
        }

        if (!actionDropdownElement.classList.contains('hidden')) {
            actionDropdownElement.classList.add('hidden');
        }
    });
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

    // Action button
    handleDropdownActionBtn();

    handleSearch();

    deleteUsers();
});
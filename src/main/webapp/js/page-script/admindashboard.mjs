import {submitForm, whenDomReady} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {handleExpertSkills} from '../shared/skills.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';

const MODE = 'mode';
const HIDDEN = 'hidden';
const ACTION = 'admindashboard';
const EXPERT_ROLE = 'expert';

const FormModes = {
    AddUser: 'add_user',
    UpdateUser: 'update_user',
    DeleteUsers: 'delete_users'
}

const EditUserControl = {
    Id: 'id',
    Name: 'name',
    Username: 'username',
    Password: 'password',
    IsBlocked: 'isBlocked'
};

const AddUserControl = {
    Name: 'name',
    Username: 'username',
    Password: 'password'
};

function handleEditUserSubmit() {
    const formData = new FormData(this.getFormElement());

    formData.set(MODE, FormModes.UpdateUser);

    submitForm(formData, ACTION, () => {
        init();
    });
}

function editUser() {
    const controls = {
        [EditUserControl.Id]: new Control('#editId', [Validators.required]),
        [EditUserControl.Name]: new Control('#editName', [Validators.required]),
        [EditUserControl.Username]: new Control('#editUsername', [Validators.required]),
        [EditUserControl.Password]: new Control('#editPassword', [Validators.required]),
        [EditUserControl.IsBlocked]: new Control('#editIsBlocked', [Validators.required])
    };

    const formGroup = new FormGroup(
        createFormConfig('#editForm', '.loader'),
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
            const roles = SelectorEngine.findOne('.role', row).innerText.trim();

            const isExpert = roles.toLowerCase().includes(EXPERT_ROLE);

            formGroup.getControl(EditUserControl.Id).setValue(id);
            formGroup.getControl(EditUserControl.Name).setValue(name);
            formGroup.getControl(EditUserControl.Username).setValue(username);
            formGroup.getControl(EditUserControl.Password).setValue(password);
            formGroup.getControl(EditUserControl.IsBlocked).setValue(isBlocked);

            if (editUserModal.classList.contains(HIDDEN)) {
                editUserModal.classList.remove(HIDDEN);
            }
        };
    });

    closeEditModalBtn.onclick = () => {
        if (!editUserModal.classList.contains(HIDDEN)) {
            editUserModal.classList.add(HIDDEN);
        }
    };
}

function handleAddUserSubmit() {
    const formData = new FormData(this.getFormElement());

    formData.set(MODE, FormModes.AddUser);

    submitForm(formData, ACTION, () => {
        init();
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
        if (confirmModalElement.classList.contains(HIDDEN)) {
            confirmModalElement.classList.remove(HIDDEN);
        }
    };
    const closeConfirmModal = () => {
        if (!confirmModalElement.classList.contains(HIDDEN)) {
            confirmModalElement.classList.add(HIDDEN);
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

        formData.set(MODE, FormModes.DeleteUsers);

        closeConfirmModal();
        submitForm(formData, ACTION, () => {
            init();
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
        createFormConfig('#addForm', '.loader'),
        controls,
        handleAddUserSubmit
    );

    const addUserBtn = SelectorEngine.findOne('#addUser');
    const addUserModal = SelectorEngine.findOne("#addUserModal");
    const closeAddUserModal = SelectorEngine.findOne('#close-add-modal');

    addUserBtn.onclick = () => {
        if (addUserModal.classList.contains(HIDDEN)) {
            addUserModal.classList.remove(HIDDEN);
        }
    };

    closeAddUserModal.onclick = () => {
        if (!addUserModal.classList.contains(HIDDEN)) {
            addUserModal.classList.add(HIDDEN);
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
                if (row.classList.contains(HIDDEN)) {
                    row.classList.remove(HIDDEN);
                }
            } else {
                if (!row.classList.contains(HIDDEN)) {
                    row.classList.add(HIDDEN);
                }
            }
        });
    }
}

function init() {
    // Edit user modal
    editUser();

    // Add user modal
    addUser();

    // Action button
    handleDropdownActionBtn();

    handleSearch();

    deleteUsers();
}

whenDomReady(() => {
    init();
});
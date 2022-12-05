import {submitForm, whenDomReady} from '../util.mjs';
import {Control, Validators} from '../entity/control.mjs';
import {createFormConfig, FormGroup} from '../entity/formGroup.mjs';
import {handleExpertSkills} from '../shared/skills.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';

const MODE = 'mode';
const HIDDEN = 'hidden';

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

    submitForm(formData, this.getAction(), () => {
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

            const id = row.querySelector('.id').innerText.trim();
            const name = row.querySelector('.name').innerText.trim();
            const username = row.querySelector('.username').innerText.trim();
            const password = row.querySelector('.password').innerText.trim();
            const isBlocked = row.querySelector('.isBlocked').innerText.trim();

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

    closeEditModalBtn.onclick = e => {
        if (!editUserModal.classList.contains(HIDDEN)) {
            editUserModal.classList.add(HIDDEN);
        }
    };
}

function handleAddUserSubmit() {
    const formData = new FormData(this.getFormElement());

    formData.set(MODE, FormModes.AddUser);

    submitForm(formData, this.getAction(), () => {
        init();
    });
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

function handleSearch() {}

function init() {
    // Edit user modal
    editUser();

    // Add user modal
    addUser();

    // Action button
    handleDropdownActionBtn();

    handleSearch();
}

whenDomReady(() => {
    init();
});
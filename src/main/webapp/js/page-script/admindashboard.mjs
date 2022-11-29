import {whenDomReady} from '../util.mjs';

whenDomReady(() => {
    const editUserModal = document.querySelector('#editUserModal');
    const editUserBtn = document.querySelector('#user');
    const closeEditModalBtn = document.querySelector('#close-edit-modal');

    const addUserModal = document.querySelector("#addUserModal");
    const addUserBtn = document.querySelector('#add-user');
    const closeAddUserModal = document.querySelector('#close-add-modal');

    const hidden = 'hidden';

    addUserBtn.onclick = e => {
        e.preventDefault();

        if (addUserModal.classList.contains(hidden)) {
            addUserModal.classList.remove(hidden);
        }
    };

    closeAddUserModal.onclick = e => {
        e.preventDefault();

        if (!addUserModal.classList.contains(hidden)) {
            addUserModal.classList.add(hidden);
        }
    };

    editUserBtn.onclick = e => {
        e.preventDefault();

        if (editUserModal.classList.contains(hidden)) {
            editUserModal.classList.remove(hidden);
        }
    };

    closeEditModalBtn.onclick = e => {
        e.preventDefault();

        if (!editUserModal.classList.contains(hidden)) {
            editUserModal.classList.add(hidden);
        }
    };
});
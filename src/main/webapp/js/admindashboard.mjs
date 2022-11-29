// const tabElements = [
//     {
//         id: 'profile',
//         triggerEl: document.querySelector('#profile-tab-example'),
//         targetEl: document.querySelector('#profile-example')
//     },
//     {
//         id: 'dashboard',
//         triggerEl: document.querySelector('#dashboard-tab-example'),
//         targetEl: document.querySelector('#dashboard-example')
//     },
//     {
//         id: 'settings',
//         triggerEl: document.querySelector('#settings-tab-example'),
//         targetEl: document.querySelector('#settings-example')
//     },
//     {
//         id: 'contacts',
//         triggerEl: document.querySelector('#contacts-tab-example'),
//         targetEl: document.querySelector('#contacts-example')
//     }
// ];

import {whenDomReady} from './util.mjs';

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
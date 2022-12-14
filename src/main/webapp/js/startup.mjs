import {whenDomReady} from './util.mjs';

whenDomReady(() => {
    selectActiveLink();
});

function selectActiveLink() {
    const links = document.querySelectorAll('.router_link');

    ([...links]).forEach(link => {
        if (window.location.pathname === link.pathname) {
            link.classList.add('underline');
        }
    });
}

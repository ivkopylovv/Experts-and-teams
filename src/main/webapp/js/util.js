whenDomReady(() => {
    const links = document.querySelectorAll('.router_link');

    ([...links]).forEach(link => {
        if (window.location.pathname === link.pathname) {
            link.classList.add('underline');
        }
    });
});

/**
 * Return formData as urlencoded string
 *
 * @param formData {FormData}
 * @returns {string}
 */
function getUrlencodedFormData(formData) {
    const params = new URLSearchParams();

    for (const pair of formData.entries()) {
        if (typeof pair[1] === 'string') {
            params.append(pair[0], pair[1]);
        }
    }

    return params.toString();
}

/**
 * Submit form asynchronously and render the resulting html
 *
 * @param formData {FormData}
 * @param url {String}
 * @param callback {Function}
 */
function ajax(formData, url, callback) {
    let redirectUrl = null;

    fetch(url, {
        method: 'post',
        body: getUrlencodedFormData(formData),
        credentials: "same-origin",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        }
    }).then(res => {
        redirectUrl = res.headers.get("X-Target");

        return res.text();
    }).then(html => {
        window.history.pushState(null, "", redirectUrl ?? "unknown");
        const page = document.querySelector('html');

        page.innerHTML = html;

        Array.from(page.querySelectorAll("script"))
            .forEach(oldScriptEl => {
                const newScriptEl = document.createElement("script");

                Array.from(oldScriptEl.attributes).forEach(attr => {
                    newScriptEl.setAttribute(attr.name, attr.value)
                });

                const scriptText = document.createTextNode(oldScriptEl.innerHTML);
                newScriptEl.appendChild(scriptText);

                oldScriptEl.parentNode.replaceChild(newScriptEl, oldScriptEl);
            });

        if (callback) {
            callback();
        }
    })
}

/**
 * Execute callback after dom content loaded event
 *
 * @param callback {Function}
 */
function whenDomReady(callback) {
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', callback);
    } else {
        callback();
    }
}

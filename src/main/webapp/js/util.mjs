/**
 * Return formData as urlencoded string
 *
 * @param formData {FormData}
 * @returns {string}
 */
export function getUrlencodedFormData(formData) {
    const params = new URLSearchParams();

    for (const pair of formData.entries()) {
        const value = pair[1];

        if (typeof value === 'string' && value !== '') {
            params.append(pair[0], value);
        }
    }

    return params.toString();
}

/**
 * Submit form asynchronously and render the resulting html
 *
 * @param reqBody {FormData | String}
 * @param url {String}
 * @param callback {Function | null}
 */
export function submitForm(reqBody, url, callback = null) {
    let redirectUrl = null;

    const body = reqBody instanceof FormData
        ? getUrlencodedFormData(reqBody)
        : reqBody;

    fetch(url, {
        method: 'post',
        body,
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        }
    }).then(res => {
        redirectUrl = res.headers.get('X-Target');

        return res.text();
    }).then(html => {
        const page = document.querySelector('html');

        const scriptSources = Array.from(document.querySelectorAll('script')).map(script => script.src);

        page.innerHTML = html;

        const newScripts = Array.from(document.querySelectorAll('script'))
            .filter(script => !scriptSources.includes(script.src));

        parseScriptTags(newScripts);

        window.history.pushState(null, '', redirectUrl ?? 'unknown');
        window.dispatchEvent(new Event('popstate'));

        if (callback) {
            callback();
        }
    })
}

function parseScriptTags(newScripts) {
    newScripts.forEach(oldScriptEl => {
        const newScriptEl = document.createElement("script");

        Array.from(oldScriptEl.attributes).forEach(attr => {
            newScriptEl.setAttribute(attr.name, attr.value)
        });

        const scriptText = document.createTextNode(oldScriptEl.innerHTML);
        newScriptEl.appendChild(scriptText);

        oldScriptEl.parentNode.replaceChild(newScriptEl, oldScriptEl);
    });
}

/**
 * Execute callback after dom content loaded event
 *
 * @param callback {Function}
 */
export function whenDomReady(callback) {
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', callback);
    } else {
        callback();
    }
}

/**
 * Return formData as urlencoded string
 *
 * @param formData {FormData}
 * @returns {string}
 */
export function getUrlencodedFormData(formData) {
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
 * @param reqBody {FormData | String}
 * @param url {String}
 * @param callback {Function}
 */
export function submitForm(reqBody, url, callback) {
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
        window.history.pushState(null, '', redirectUrl ?? 'unknown');
        const page = document.querySelector('html');

        const scriptSources = Array.from(document.querySelectorAll('script')).map(script => script.src);

        page.innerHTML = html;

        const newScriptSources = Array.from(document.querySelectorAll('script'))
            .map(script => script.src)
            .filter(source => !scriptSources.includes(source));

        parseScriptTags(page);

        if (callback) {
            callback();
        }
    })
}

function parseScriptTags(page) {
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

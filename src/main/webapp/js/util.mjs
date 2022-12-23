export function mapFormDataToObject(formData) {
    const result = {};

    for (const [key, value] of formData.entries()) {
        if (value !== '') {
            result[key] = value;
        }
    }

    return result;
}

export function redirect(path) {
    const url = [
        window.location.origin,
        'experts-and-teams',
        path
    ].join('/');

    window.location.href = url;
}

export function reload() {
    window.location.reload();
}

export async function makeRequest(url, options, plainText = true) {
    const body = options.body ? JSON.stringify(options.body) : undefined;
    const query = options.query ? `?${new URLSearchParams(options.query).toString()}` : '';
    const method = options.method ?? 'get';

    const response = await fetch(url + query, {
        method,
        body,
        credentials: 'same-origin',
        headers: {'Content-Type': 'application/json'}
    });

    const status = response.status;
    const data = plainText
        ? await response.text()
        : await response.json();

    return {status, data};
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

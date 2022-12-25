import {HIDDEN_CLASS} from '../const/global.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';

const FORM_SELECTOR = 'formSelector';
const LOADER_SELECTOR = 'loaderSelector';

export function createFormConfig(formSelector, loaderSelector) {
    return {
        [FORM_SELECTOR]: formSelector,
        [LOADER_SELECTOR]: loaderSelector
    };
}

export class FormGroup {
    /**
     * @param {Object} config
     * @param {Object} controls
     * @param {Function} onSubmit
     * @param {Function[]} validators
     */
    constructor(config = {}, controls, onSubmit, validators = []) {
        this._formElement = SelectorEngine.findOne(config[FORM_SELECTOR]);
        this._loaderElement = SelectorEngine.findOne(config[LOADER_SELECTOR] ?? '.loader');
        this._controls = controls;
        this._onSubmit = onSubmit;
        this._validators = validators;

        this._bindValidators();
        this._handleSubmit();
    }

    getControl(controlName) {
        return this._controls[controlName];
    }

    getFormElement() {
        return this._formElement;
    }

    getAction() {
        return this._formElement.action;
    }

    clear() {
        Object.getOwnPropertyNames(this._controls)
            .map(name => {
                const control = this._controls[name];

                control.clear();
            });
    }

    _bindValidators() {
        this._validators = this._validators.map(validator => validator.bind(this));
    }

    _showLoader() {
        if (!this._loaderElement) {
            return;
        }

        this._loaderElement.classList.remove(HIDDEN_CLASS);
    }

    _hideLoader() {
        if (!this._loaderElement) {
            return;
        }

        if (!this._loaderElement.classList.contains(HIDDEN_CLASS)) {
            this._loaderElement.classList.add(HIDDEN_CLASS);
        }
    }

    _validateForm() {
        return this._validators
            .map(validator => validator())
            .every(valid => valid);
    }

    _validateControls() {
        return Object.getOwnPropertyNames(this._controls)
            .map(name => {
                const control = this._controls[name];
                const controlValid = control.validate();

                if (!controlValid) {
                    control.showError();
                }

                return controlValid;
            })
            .every(controlValid => controlValid);
    }

    _handleSubmit() {
        this._formElement.onsubmit = event => {
            event.preventDefault();
            this._showLoader();

            const formValid = this._validateControls() && this._validateForm();

            if (!formValid) {
                this._hideLoader();

                return;
            }

            this._onSubmit();
            this._hideLoader();
        };
    }
}

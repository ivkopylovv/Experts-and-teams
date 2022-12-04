import {SelectorEngine} from '../dom/selector-engine.mjs';

export class FormGroup {
    constructor(formSelector, loaderSelector, controls, onSubmit) {
        this._formElement = SelectorEngine.findOne(formSelector);
        this._loaderElement = SelectorEngine.findOne(loaderSelector);
        this._controls = controls;
        this._onSubmit = onSubmit;

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

    _showLoader() {
        this._loaderElement.style.display = 'inline';
    }

    _hideLoader() {
        this._loaderElement.style.display = 'none';
    }

    _formValid() {
        return Object.getOwnPropertyNames(this._controls).every(name =>
            this._controls[name].validate()
        );
    }

    _validateControls() {
        Object.getOwnPropertyNames(this._controls).forEach(name => {
            const control = this._controls[name];
            const controlValid = control.validate();

            if (!controlValid) {
                control.showError();
            }
        });
    }

    _handleSubmit() {
        this._formElement.onsubmit = event => {
            event.preventDefault();

            this._showLoader();
            this._validateControls();

            const formValid = this._formValid();

            if (!formValid) {
                this._hideLoader();

                return;
            }

            this._onSubmit();
        };
    }
}

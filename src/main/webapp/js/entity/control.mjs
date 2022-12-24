import {HIDDEN_CLASS} from '../const/global.mjs';
import {SelectorEngine} from '../dom/selector-engine.mjs';

const ERROR_CONTROL_BORDER = 'border-red-500';

export const Validators = {
    required: function () {
        return this.getValue() !== '';
    }
};

export class Control {
    /**
     * @param {String} selector
     * @param {Function[]} validators
     */
    constructor(selector, validators = []) {
        this._element = SelectorEngine.findOne(selector);
        this._errorElement = SelectorEngine.findOne(this._getErrorNodeSelector());
        this._validators = validators;
        this._offErrorFn = this.offError.bind(this);

        this._bindValidators();
        this._handleOffError();
    }

    getElement() {
        return this._element;
    }

    getValue() {
        return this._element.value?.trim() ?? '';
    }

    setValue(value) {
        this._element.value = value;
    }

    validate() {
        return this._validators.map(validator => validator()).every(valid => valid);
    }

    showError() {
        this._element.classList.add(ERROR_CONTROL_BORDER);

        if (this._errorElement && this._errorElement.classList.contains(HIDDEN_CLASS)) {
            this._errorElement.classList.remove(HIDDEN_CLASS);
        }
    }

    offError() {
        this._element.classList.remove(ERROR_CONTROL_BORDER);

        if (this._errorElement && !this._errorElement.classList.contains(HIDDEN_CLASS)) {
            this._errorElement.classList?.add(HIDDEN_CLASS);
        }
    }

    clear() {
        this.setValue('');
        this.offError();
    }

    destroy() {
        this.getElement().removeEventListener('input', this._offErrorFn)
    }

    _bindValidators() {
        this._validators = this._validators.map(validator => validator.bind(this));
    }

    _getErrorNodeSelector() {
        return `.error_${this._element.name}`;
    }

    _handleOffError() {
        this.getElement().addEventListener('input', this._offErrorFn)
    }
}

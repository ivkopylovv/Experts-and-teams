export class Control {
    /**
     * @param node {HTMLInputElement}
     * @param validateFn {Function}
     */
    constructor(node, validateFn) {
        this.node = node;
        this.errorNode = null;
        this.validateFn = validateFn;
    }

    getValue() {
        return this.node.value?.trim() ?? '';
    }

    validate() {
        if (!this.validateFn) {
            return true;
        }

        return this.validateFn(this.node);
    }

    showError() {
        if (!this.errorNode) {
            this.errorNode = this.findErrorControl();
        }

        this.node.classList.add('border-red-500');
        if (this.errorNode) {
            this.errorNode.style.display = 'block';
        }
    }

    findErrorControl() {
        return this.node.parentNode.querySelector(`.error_${this.node.name}`);
    }

    offError() {
        if (!this.errorNode) {
            this.errorNode = this.findErrorControl();
        }

        this.node.classList.remove('border-red-500');
        if (this.errorNode) {
            this.errorNode.style.display = 'none';
        }
    }
}

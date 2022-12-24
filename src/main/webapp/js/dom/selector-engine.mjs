export const SelectorEngine = {
    find(selector, element = document.documentElement) {
        return [].concat(...Element.prototype.querySelectorAll.call(element, selector))
    },
    findOne(selector, element = document.documentElement) {
        return Element.prototype.querySelector.call(element, selector)
    },
    children(element, selector) {
        return [].concat(...element.children).filter(child => child.matches(selector))
    },
    parents(element, selector) {
        const parents = []
        let ancestor = element.parentNode.closest(selector)

        while (ancestor) {
            parents.push(ancestor)
            ancestor = ancestor.parentNode.closest(selector)
        }

        return parents
    },
    // TODO: remove restriction
    // There is one restriction, that first child element in template must be div
    importElement(selector) {
        const templateElement = document.querySelector(selector);

        return templateElement.content.cloneNode(true);
    }
}
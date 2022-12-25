<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template id="notification-template">
    <div class="notification py-3 px-4 flex items-start space-x-2">
        <div class="flex-1">
            <div class="message text-gray-500 text-sm mb-1.5"></div>
            <div class="date text-xs text-blue-600">3 hours ago</div>
        </div>
        <button
            type="button"
            data-notification-id=""
            class="remove-btn font-medium text-red-600"
        >
            <span class="material-symbols-outlined font-medium">close</span>
        </button>
    </div>
</template>
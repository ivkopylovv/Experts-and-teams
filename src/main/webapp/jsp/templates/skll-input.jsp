<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template id="skill-template">
    <div class="flex items-center">
        <input type="text" name="skill"
               class="mr-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
        <button type="button"
                class="delete flex items-center rounded-lg text-gray-900 bg-white focus:outline-none enabled:hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium text-sm p-2 disabled:opacity-75">
            <span class="material-symbols-outlined text-lg">delete</span>
        </button>
    </div>
</template>

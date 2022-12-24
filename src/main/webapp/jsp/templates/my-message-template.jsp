<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template id="my-message-template">
    <div class="flex w-full mt-2 space-x-3 max-w-xs ml-auto justify-end">
        <div>
            <div class="bg-blue-500 text-white p-3 rounded-lg">
                <p class="font-medium message-user-name"></p>
                <p class="text-sm message-body"></p>
            </div>
            <span class="message-date text-xs text-gray-500 leading-none block mt-1"></span>
        </div>
    </div>
</template>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="bg-blue-800 py-4 text-white fixed top-0 left-0 right-0 z-20">
    <div class="container mx-auto xl:max-w-7xl flex items-center justify-between">
        <div class="text-xl font-medium">Experts and Teams</div>

        <div class="flex items-center space-x-2">
            <button id="notification-btn"
                    class="inline-flex items-center text-sm font-medium text-center text-white hover:text-gray-100 focus:outline-none"
                    type="button"
                    data-dropdown-toggle="notification-dropdown"
            >
                <svg class="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20"
                     xmlns="http://www.w3.org/2000/svg">
                    <path d="M10 2a6 6 0 00-6 6v3.586l-.707.707A1 1 0 004 14h12a1 1 0 00.707-1.707L16 11.586V8a6 6 0 00-6-6zM10 18a3 3 0 01-3-3h6a3 3 0 01-3 3z"></path>
                </svg>
            </button>

            <div id="notification-dropdown"
                 class="hidden rounded-lg overflow-hidden z-20 w-full max-w-sm bg-white divide-y divide-gray-100 shadow"
                 aria-labelledby="notification-btn">
                <div class="block py-2 px-4 font-medium text-center text-gray-700 bg-gray-50">
                    Notifications
                </div>
                <div id="notification-container" class="divide-y divide-gray-100 max-h-80 overflow-y-auto"></div>
            </div>

            <nav class="flex items-center space-x-2 text-sm">
                <a class="router_link font-medium hover:underline" href="/experts-and-teams/logout">Logout</a>
            </nav>
        </div>
    </div>
</header>

<%@include file="/jsp/templates/notification-message.jsp" %>
<%@include file="/jsp/templates/empty-notification.jsp" %>

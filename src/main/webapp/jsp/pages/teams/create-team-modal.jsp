<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="create-team-modal" tabindex="-1" aria-hidden="true"
     class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center p-6 w-full md:inset-0 h-modal md:h-full">
    <div class="relative w-full max-w-md h-full md:h-auto">
        <form id="create-team-form" class="relative bg-white rounded-lg shadow">
            <div class="flex justify-between items-start p-6 rounded-t">
                <h3 class="ml-7 flex-1 text-2xl font-medium text-gray-900 text-center">
                    New Team
                </h3>
                <button
                        type="button"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
                        data-modal-toggle="create-team-modal">
                    <%@include file="/jsp/icons/close.jsp" %>
                </button>
            </div>
            <div class="p-6 pt-0 space-y-6">
                <input
                        id="create-team-name"
                        name="name"
                        class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5"
                        placeholder="Team name"
                >
                <p id="create-team-name-error" class="mt-2 text-sm text-red-600 hidden">Field is empty</p>
            </div>
            <div class="flex items-center justify-center p-6 space-x-2 rounded-b">
                <button class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-6 py-3 focus:outline-none">
                    Create
                </button>
                <button
                        type="button"
                        data-modal-toggle="create-team-modal"
                        class="cancel text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-200 rounded-lg border border-gray-200 text-sm font-medium px-6 py-3 hover:text-gray-900 focus:z-10">
                    Cancel
                </button>
            </div>
        </form>
    </div>
</div>

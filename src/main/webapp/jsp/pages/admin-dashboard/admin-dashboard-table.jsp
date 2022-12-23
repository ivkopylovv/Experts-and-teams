<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="ru.rsreu.expertsandteams.support.helper.UserHelper" %>
<div class="w-3/5 mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
    <div class="p-4 bg-white">
        <label for="table-search-users" class="sr-only">Search</label>
        <div class="relative">
            <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
                <svg class="w-5 h-5 text-gray-500" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                          clip-rule="evenodd"></path>
                </svg>
            </div>
            <input type="text" id="table-search-users"
                   class="block p-2 pl-10 w-80 text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
                   placeholder="Search for users">
        </div>
    </div>
    <table class="w-full text-sm text-left text-gray-500">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50">
        <tr class="">
            <th scope="col" class="py-3 px-6">
                Name
            </th>
            <th scope="col" class="py-3 px-6">
                Username
            </th>
            <th scope="col" class="py-3 px-6">
                Password
            </th>
            <th scope="col" class="py-3 px-6">
                Status
            </th>
            <th scope="col" class="py-3 px-6">
                Role
            </th>
            <th scope="col" class="py-3 px-6">
                Action
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr class="table-row bg-white border-b hover:bg-gray-50">
                <td class="id hidden">
                        ${user.getId()}
                </td>
                <td class="name py-4 px-6">
                        ${user.getName()}
                </td>
                <td class="username py-4 px-6">
                        ${user.getUsername()}
                </td>
                <td class="password py-4 px-6">
                        ${user.getPassword()}
                </td>
                <td class="py-4 px-6">
                    <div class="flex items-center">
                        <div class="h-2.5 w-2.5 rounded-full ${user.getOnline() ? "bg-green-400" : "bg-red-500"} mr-2"></div>
                            ${user.getOnline() ? "Online" : "Offline"}
                    </div>
                </td>
                <td class="role py-4 px-6">
                        ${user.getRole()}
                </td>
                <td class="isBlocked hidden">
                        ${user.getBlocked()}
                </td>
                <td class="py-4 px-6">
                    <div class="flex items-center space-x-2">
                        <button
                                type="button"
                                data-modal-toggle="edit-user-modal"
                                class="edit-btn font-medium text-blue-600 hover:underline"
                        >
                            Edit user
                        </button>
                        <button
                                type="button"
                                class="delete-btn font-medium text-red-600 hover:underline"
                                data-modal-toggle="confirm-delete-modal"
                                data-user-id="${user.getId()}"
                        >
                            Delete user
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="mt-2">
        <button id="add-user" type="button"
                class="flex items-center ml-auto text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5"
                data-modal-toggle="add-user-modal"
        >
            <span class="material-symbols-outlined text-base font-bold mr-1">add</span>
            Add user
        </button>
    </div>
</div>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="ru.rsreu.expertsandteams.support.helper.UserHelper" %>
<div class="w-3/5 mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
    <div class="flex justify-between items-center p-4 bg-white">
        <div>
            <button id="actionDropdownBtn"
                    class="inline-flex items-center text-gray-500 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-3 py-1.5"
                    type="button"
            >
                <span class="sr-only">Action button</span>
                Action
                <svg class="ml-2 w-3 h-3" aria-hidden="true" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path>
                </svg>
            </button>
            <!-- Dropdown menu -->
            <div id="actionDropdown"
                 class="absolute hidden z-10 w-44 bg-white rounded divide-y divide-gray-100 shadow">
                <ul class="py-1 text-sm text-gray-700">
                    <li>
                        <button id="addUser" type="button"
                                class="dropdown-item block py-2 px-4 hover:bg-gray-100 w-full text-left">Add user
                        </button>
                    </li>
                    <li>
                        <button id="deleteUsers"
                                type="button"
                                data-modal-toggle="#confirm-delete-modal"
                                class="dropdown-item block py-2 px-4 hover:bg-gray-100 w-full text-left">
                            Delete users
                        </button>
                    </li>
                </ul>
            </div>
        </div>
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
            <th scope="col" class="p-4">
                <div class="flex items-center">
                    <input
                            id="select-all-checkboxes"
                            type="checkbox"
                            value=""
                            class="w-4 h-4 text-blue-600 bg-gray-100 rounded border-gray-300 focus:ring-blue-500 ">
                </div>
            </th>
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
        <c:forEach items="${users}" var="u">
            <tr class="table-row bg-white border-b hover:bg-gray-50">
                <td class="p-4 w-4">
                    <div class="flex items-center">
                        <input type="checkbox"
                               value=""
                            ${u.getId() == user.getId() ? "disabled" : ""}
                               data-user-id="${u.getId()}"
                               class="delete-user-checkbox w-4 h-4 text-blue-600 bg-gray-100 rounded border-gray-300 focus:ring-blue-500"
                        >
                    </div>
                </td>
                <td class="id hidden">
                        ${u.getId()}
                </td>
                <td class="name py-4 px-6">
                        ${u.getName()}
                </td>
                <td class="username py-4 px-6">
                        ${u.getUsername()}
                </td>
                <td class="password py-4 px-6">
                        ${u.getPassword()}
                </td>
                <td class="py-4 px-6">
                    <div class="flex items-center">
                        <div class="h-2.5 w-2.5 rounded-full ${u.isOnline() ? "bg-green-400" : "bg-red-500"} mr-2"></div>
                            ${u.isOnline() ? "Online" : "Offline"}
                    </div>
                </td>
                <td class="role py-4 px-6">
                        ${UserHelper.getRolesAsString(u.getRoles())}
                </td>
                <td class="isBlocked hidden">
                        ${u.getBlocked()}
                </td>
                <td class="py-4 px-6">
                    <button type="button" class="edit-btn font-medium text-blue-600 hover:underline">
                        Edit user
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

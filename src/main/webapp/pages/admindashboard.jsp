<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@
        page
        contentType="text/html;charset=UTF-8"
        language="java"
        import="ru.rsreu.expertsandteams.helper.UserHelper"
%>
<html>
<head>
    <title>Sign In</title>
    <%@include file="../templates/meta.jsp" %>
    <script type="module" src="../js/shared/skills.mjs"></script>
    <script type="module" src="../js/page-script/admindashboard.mjs"></script>
</head>
<body>
<%@include file="../templates/header.jsp" %>
<div class="body flex flex-col items-center justify-center">
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
                <div id="actionDropdown" class="absolute hidden z-10 w-44 bg-white rounded divide-y divide-gray-100 shadow">
                    <ul class="py-1 text-sm text-gray-700">
                        <li>
                            <button id="addUser" type="button"
                                    class="dropdown-item block py-2 px-4 hover:bg-gray-100 w-full text-left">Add user
                            </button>
                        </li>
                        <li>
                            <button id="deleteUsers" type="button"
                                    class="dropdown-item block py-2 px-4 hover:bg-gray-100 w-full text-left">Delete users
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
            <tr>
                <th scope="col" class="p-4">
                    <div class="flex items-center">
                        <input type="checkbox" value=""
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
            <c:forEach items="${users}" var="user">
                <tr class="table-row bg-white border-b hover:bg-gray-50">
                    <td class="p-4 w-4">
                        <div class="flex items-center">
                            <input type="checkbox" value=""
                                   class="w-4 h-4 text-blue-600 bg-gray-100 rounded border-gray-300 focus:ring-blue-500">
                        </div>
                    </td>
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
                            <div class="h-2.5 w-2.5 rounded-full ${user.isOnline() ? "bg-green-400" : "bg-red-500"} mr-2"></div>
                                ${user.isOnline() ? "Online" : "Offline"}
                        </div>
                    </td>
                    <td class="role py-4 px-6">
                        ${UserHelper.getRolesAsString(user.getRoles())}
                    </td>
                    <td class="isBlocked hidden">
                            ${user.getBlocked()}
                    </td>
                    <td class="py-4 px-6">
                        <button type="button" class="edit-btn font-medium text-blue-600 hover:underline">Edit user
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Edit user modal -->
<div id="editUserModal"
     class="flex hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center p-4 w-full md:inset-0 h-modal md:h-full bg-gray-200/60">
    <div class="relative w-full max-w-2xl h-full md:h-auto">
        <!-- Modal content -->
        <form id="editForm" action="admindashboard" class="relative bg-white rounded-lg shadow">
            <!-- Modal header -->
            <div class="flex justify-between items-start p-4 rounded-t border-b">
                <h3 class="text-xl font-semibold text-gray-900">
                    Edit user
                </h3>
                <button id="close-edit-modal" type="button"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
                        data-modal-toggle="editUserModal">
                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                              d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                              clip-rule="evenodd"></path>
                    </svg>
                </button>
            </div>
            <!-- Modal body -->
            <div class="p-6 space-y-6">
                <div>
                    <label for="editName" class="block mb-2 text-sm font-medium text-gray-900">Name</label>
                    <input type="text" id="editName"
                           name="name"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">
                    <p class="error_name mt-2 text-sm text-red-600 hidden">Field is empty</p>
                </div>

                <div>
                    <label for="editUsername" class="block mb-2 text-sm font-medium text-gray-900">Username</label>
                    <input type="text" id="editUsername"
                           name="username"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">
                    <p class="error_username mt-2 text-sm text-red-600 hidden">Field is empty</p>
                </div>

                <div>
                    <label for="editPassword" class="block mb-2 text-sm font-medium text-gray-900">Password</label>
                    <input type="text" id="editPassword"
                           name="password"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">
                    <p class="error_password mt-2 text-sm text-red-600 hidden">Field is empty</p>
                </div>

                <input class="hidden" type="text" id="editId" name="id">
                <input class="hidden" type="text" id="editIsBlocked" name="is_blocked">
            </div>
            <!-- Modal footer -->
            <div class="flex items-center p-6 space-x-2 rounded-b border-t border-gray-200">
                <button type="submit"
                        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    <%@include file="../templates/button-loader.jsp" %>
                    Save
                </button>
            </div>
        </form>
    </div>
</div>

<!-- Add user modal -->
<div id="addUserModal"
     class="flex hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center p-4 w-full md:inset-0 h-modal md:h-full bg-gray-200/60">
    <div class="relative w-full max-w-2xl h-full md:h-auto">
        <!-- Modal content -->
        <form id="addForm" action="admindashboard" class="relative bg-white rounded-lg shadow">
            <!-- Modal header -->
            <div class="flex justify-between items-start p-4 rounded-t border-b">
                <h3 class="text-xl font-semibold text-gray-900">
                    Add user
                </h3>
                <button id="close-add-modal" type="button"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
                        data-modal-toggle="editUserModal">
                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                              d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                              clip-rule="evenodd"></path>
                    </svg>
                </button>
            </div>
            <!-- Modal body -->
            <div class="p-6 space-y-6">
                <div>
                    <label for="addName" class="block mb-2 text-sm font-medium text-gray-900">Name</label>
                    <input type="text" id="addName"
                           name="name"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">

                    <p class="error_name mt-2 text-sm text-red-600 hidden">Field is empty</p>
                </div>

                <div>
                    <label for="addUsername" class="block mb-2 text-sm font-medium text-gray-900">Username</label>
                    <input type="text" id="addUsername"
                           name="username"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">
                    <p class="error_username mt-2 text-sm text-red-600 hidden">Field is empty</p>
                </div>

                <div>
                    <label for="addPassword" class="block mb-2 text-sm font-medium text-gray-900">Password</label>
                    <input type="password" id="addPassword"
                           name="password"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">
                    <p class="error_password mt-2 text-sm text-red-600 hidden">Field is empty</p>
                </div>

                <div id="role" class="flex">
                    <div class="flex items-center mr-4">
                        <input checked type="radio" value="user" name="role"
                               class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
                        <label class="ml-2 text-sm font-medium text-gray-900">User</label>
                    </div>
                    <div class="flex items-center mr-4">
                        <input type="radio" value="expert" name="role"
                               class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
                        <label class="ml-2 text-sm font-medium text-gray-900">Expert</label>
                    </div>
                    <div class="flex items-center mr-4">
                        <input type="radio" value="moderator" name="role"
                               class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
                        <label class="ml-2 text-sm font-medium text-gray-900">Moderator</label>
                    </div>
                    <div class="flex items-center mr-4">
                        <input type="radio" value="admin" name="role"
                               class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
                        <label class="ml-2 text-sm font-medium text-gray-900">Administrator</label>
                    </div>
                </div>

                <%@include file="../templates/skills-container.jsp" %>
            </div>
            <!-- Modal footer -->
            <div class="flex items-center p-6 space-x-2 rounded-b border-t border-gray-200">
                <button type="submit"
                        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    <%@include file="../templates/button-loader.jsp" %>
                    Add
                </button>
            </div>
        </form>
    </div>
</div>

<%@include file="../templates/skll-input.jsp" %>
</body>
</html>

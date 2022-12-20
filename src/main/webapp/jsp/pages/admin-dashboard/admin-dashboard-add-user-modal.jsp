<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="addUserModal"
     class="flex justify-center items-center bg-gray-200/60 hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 p-4 w-full md:inset-0 h-modal md:h-full">
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

                <%@include file="/jsp/shared/skills-container.jsp" %>
            </div>

            <!-- Modal footer -->
            <div class="flex items-center p-6 space-x-2 rounded-b border-t border-gray-200">
                <button type="submit"
                        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    <%@include file="/jsp/icons/button-loader.jsp" %>
                    Add
                </button>
            </div>
        </form>
    </div>
</div>

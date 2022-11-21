<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <%@include file="../templates/meta.jsp" %>
    <script type="text/javascript" src="../js/signup.js"></script>
</head>
<body>
<%@include file="../templates/header.jsp" %>
<div class="flex flex-col items-center justify-center mt-8">
    <form id="signup" class="w-full max-w-sm" action="signup" method="post">
        <h1 class="text-xl mb-5 bg-gradient-to-br from-green-400 to-blue-600 rounded-t-lg p-2 text-white">Sign Up</h1>

        <div class="mb-4">
            <label for="username" class="block mb-2 text-sm font-medium text-gray-900">Username</label>
            <input type="text" id="username"
                   name="username"
                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ">
        </div>

        <div class="mb-5">
            <label for="password" class="block mb-2 text-sm font-medium text-gray-900">Password</label>
            <input type="password" id="password"
                   name="password"
                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
        </div>

        <div class="mb-5">
            <label for="confirmPassword" class="block mb-2 text-sm font-medium text-gray-900">Confirm Password</label>
            <input type="password" id="confirmPassword"
                   name="confirmPassword"
                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
        </div>

        <div class="flex items-center mb-5">
            <input id="isExpert" type="checkbox" value=""
                   class="w-4 h-4 text-blue-600 bg-gray-100 rounded border-gray-300 focus:ring-blue-500">
            <label for="isExpert" class="ml-2 text-sm font-medium text-gray-900">I'm expert</label>
        </div>

        <button type="submit"
                class="text-white bg-gradient-to-br from-green-400 to-blue-600 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-green-200  font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2">
            Sign Up
        </button>
    </form>
</div>
</body>
</html>

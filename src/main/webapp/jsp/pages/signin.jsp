<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <%@include file="/jsp/shared/meta.jsp" %>
</head>
<body id="signin">
<%@include file="/jsp/shared/header-unauth.jsp" %>
<div class="body flex flex-col items-center justify-center">
    <form id="signin-form" class="w-full max-w-sm mt-8 p-6 bg-white border border-gray-200 rounded-lg shadow-md">
        <h1 class="text-2xl mb-2 text-center">Sign In</h1>

        <div class="mb-4">
            <label for="username" class="block mb-2 text-sm font-medium text-gray-900">Username</label>
            <input type="text" id="username"
                   name="username"
                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ${controlsInvalid ? 'border border-red-500' : ''}">
            <p class="error_username mt-2 text-sm text-red-600 hidden">Invalid username or password</p>
        </div>

        <div class="mb-5">
            <label for="password" class="block mb-2 text-sm font-medium text-gray-900">Password</label>
            <input type="password" id="password"
                   name="password"
                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 ${controlsInvalid ? 'border border-red-500' : ''}"
            >
            <p class="error_password mt-2 text-sm text-red-600 hidden">Invalid username or password</p>
        </div>

        <button
                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 focus:outline-none">
            <%@include file="/jsp/icons/button-loader.jsp" %>
            Sign In
        </button>
    </form>
</div>
<%@include file="/jsp/shared/scripts.jsp" %>
</body>
</html>

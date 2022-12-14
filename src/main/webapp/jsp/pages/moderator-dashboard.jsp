<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <%@include file="/jsp/shared/meta.jsp" %>
</head>
<body id="moderator-dashboard">
<%@include file="/jsp/shared/header.jsp" %>

<div class="body">
    <div class="w-3/5 mx-auto mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
        <div class="overflow-y-auto max-h-[66%] relative">
            <table class="w-full text-sm text-left text-gray-500 mb-2">
                <thead class="sticky top-0 text-xs text-gray-700 uppercase bg-gray-50">
                    <tr>
                        <th scope="col" class="py-3 px-6">
                            Name
                        </th>
                        <th scope="col" class="py-3 px-6">
                            Username
                        </th>
                        <th scope="col" class="py-3 px-6">
                            Is blocked
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
                        <td class="is-blocked py-4 px-6">
                            <div class="flex items-center">
                                <input
                                        type="checkbox"
                                        value=""
                                    ${user.getBlocked() ? "checked" : ""}
                                        class="block-checkbox w-4 h-4 text-blue-600 bg-gray-100 rounded border-gray-300 focus:ring-blue-500"
                                >
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="changes-alert"
             class="hidden flex items-center justify-between py-2 px-4 text-base text-yellow-700 bg-yellow-100 rounded-lg">
            <div>There is <span id="changes-count" class="font-bold">0</span> changes</div>
            <button
                    id="apply-changes"
                    class="px-5 py-2.5 focus:outline-none text-white bg-yellow-400 hover:bg-yellow-500 focus:ring-4 focus:ring-yellow-300 font-medium rounded-lg text-sm"
                    type="button"
            >Apply
            </button>
        </div>
    </div>
</div>

<%@include file="/jsp/shared/scripts.jsp" %>
</body>
</html>

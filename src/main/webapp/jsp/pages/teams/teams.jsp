<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/jsp/shared/meta.jsp" %>
</head>
<body class="user-dashboard">
<%@include file="/jsp/shared/header.jsp" %>

<div class="container mx-auto pt-14">
    <div class="w-3/5 overflow-hidden mx-auto mt-8 relative">
        <div class="flex items-center flex-nowrap w-full p-4 space-x-2 mb-3 bg-white border border-gray-200 rounded-lg shadow-sm">
            <button
                    type="button"
                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-2.5 py-1.5 focus:outline-none"
                    data-modal-toggle="create-team-modal"
            >
                Create team
            </button>
            <button
                    type="button"
                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-2.5 py-1.5 focus:outline-none"
                    data-modal-toggle="join-team-modal">
                Join to team
            </button>
        </div>
        <div class="overflow-y-auto max-h-[66%] border border-gray-200 rounded-lg shadow-sm">
            <table class="w-full text-sm text-left text-gray-500">
                <thead class="sticky top-0 text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="py-3 px-6">
                        Team Name
                    </th>
                    <th scope="col" class="py-3 px-6">
                        Members Count
                    </th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr class="table-row bg-white border-b hover:bg-gray-50">
                    <td class="hidden team-id py-4 px-6 font-semibold">123</td>
                    <td class="team-name py-4 px-6 font-semibold">Team 1</td>
                    <td class="team-members-count py-4 px-6">12</td>
                    <td>
                        <button type="button"
                                class="leave-btn text-lg text-blue-600"
                                data-modal-toggle="confirm-leave-modal"
                        >
                            <span class="material-symbols-outlined font-bold">logout</span>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="/jsp/pages/user-teams/confirm-leave-modal.jsp" %>
<%@include file="/jsp/pages/user-teams/create-team-modal.jsp" %>
<%@include file="/jsp/pages/user-teams/join-team-modal.jsp" %>

<%@include file="/jsp/shared/scripts.jsp" %>
</body>
</html>
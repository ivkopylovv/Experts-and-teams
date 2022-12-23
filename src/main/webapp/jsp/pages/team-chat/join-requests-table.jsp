<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="w-full text-sm text-left text-gray-500">
    <thead class="sticky top-0 text-xs text-gray-700 uppercase bg-gray-50">
    <tr>
        <th scope="col" class="py-3 px-6">Name</th>
        <th scope="col" class="py-3 px-6">Message</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <tr class="table-row bg-white border-b hover:bg-gray-50">
        <td class="hidden request-id py-4 px-6 font-semibold">123</td>
        <td class="request-user-name py-4 px-6 font-semibold">
            User 1
        </td>
        <td class="request-message py-4 px-6">
            I have big dick!!!
        </td>
        <td>
            <div class="flex items-center space-x-3">
                <button
                        type="button"
                        class="accept text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-3 py-1.5 focus:outline-none"
                >
                    Accept
                </button>
                <button
                        type="button"
                        class="cancel text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-200 rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 hover:text-gray-900 focus:z-10">
                    Cancel
                </button>
            </div>
        </td>
    </tr>
    </tbody>
</table>

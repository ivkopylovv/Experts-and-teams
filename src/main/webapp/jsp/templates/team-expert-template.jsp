<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template id="team-expert-template">
    <tr class="table-row bg-white border-b hover:bg-gray-50">
        <td class="py-4 px-6">
            <p class="expert-name font-semibold"></p>
            <p class="expert-skill text-sm text-gray-600"></p>
        </td>
        <td class="py-3 pr-12">
            <div class="flex items-center w-full justify-end">
                <button
                    data-expert-id=""
                    type="button"
                    class="block-expert text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-3 py-1.5 focus:outline-none"
                >Block</button>
            </div>
        </td>
    </tr>
</template>
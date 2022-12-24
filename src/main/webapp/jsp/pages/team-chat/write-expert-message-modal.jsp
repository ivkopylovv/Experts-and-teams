<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="write-expert-message-modal" tabindex="-1" aria-hidden="true"
     class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center p-6 w-full md:inset-0 h-modal md:h-full">
    <div class="relative w-full max-w-2xl h-full md:h-auto">
        <form id="write-expert-message-form" class="relative bg-white rounded-lg shadow">
            <div class="flex justify-between items-start p-6 rounded-t">
                <h3 class="ml-7 flex-1 text-2xl font-medium text-gray-900 text-center">
                    Write To Expert
                </h3>
                <button
                        type="button"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
                        data-modal-toggle="write-expert-message-modal">
                    <%@include file="/jsp/icons/close.jsp" %>
                </button>
            </div>
            <div class="p-6 pt-0 space-y-6">
                <div class="overflow-y-auto max-h-[70%] border border-gray-200 rounded-lg shadow-sm">
                    <table class="w-full text-sm text-left text-gray-500">
                        <thead class="sticky top-0 text-xs text-gray-700 uppercase bg-gray-50">
                            <tr>
                                <th scope="col" class="py-3 px-6">
                                    Expert Name
                                </th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody id="write-expert-container"></tbody>
                    </table>
                </div>
            </div>
            <div class="p-6 space-x-2 rounded-b">
                <textarea
                        id="expert-message-input"
                        rows="4"
                        class="resize-none block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
                        placeholder="Write message..."
                ></textarea>
            </div>
        </form>
    </div>
</div>

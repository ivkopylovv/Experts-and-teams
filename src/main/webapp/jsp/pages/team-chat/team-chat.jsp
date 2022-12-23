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
        <div class="flex items-center justify-between flex-nowrap w-full p-4 space-x-2 mb-3 bg-white border border-gray-200 rounded-lg shadow-sm">
            <button
                    type="button"
                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-2.5 py-1.5 focus:outline-none"
                    data-modal-toggle="write-expert-message-modal">
                Write to expert
            </button>
            <button type="button"
                    class="join-btn text-lg text-blue-600"
                    data-modal-toggle="join-team-modal">
                <span class="material-symbols-outlined font-bold">settings</span>
            </button>
        </div>

        <div class="h-4/6 flex flex-col flex-grow w-full bg-white rounded-lg overflow-hidden border border-gray-200 shadow-sm">
            <div class="flex flex-col flex-grow h-0 p-4 overflow-auto">
                <div class="flex w-full mt-2 space-x-3 max-w-xs">
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                    <div>
                        <div class="bg-gray-300 p-3 rounded-r-lg rounded-bl-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs ml-auto justify-end">
                    <div>
                        <div class="bg-blue-600 text-white p-3 rounded-l-lg rounded-br-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                                eiusmod.</p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs ml-auto justify-end">
                    <div>
                        <div class="bg-blue-600 text-white p-3 rounded-l-lg rounded-br-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet.</p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs">
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                    <div>
                        <div class="bg-gray-300 p-3 rounded-r-lg rounded-bl-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                                tempor
                                incididunt ut labore et dolore magna aliqua. </p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs ml-auto justify-end">
                    <div>
                        <div class="bg-blue-600 text-white p-3 rounded-l-lg rounded-br-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                                tempor
                                incididunt ut labore et dolore magna aliqua. </p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs ml-auto justify-end">
                    <div>
                        <div class="bg-blue-600 text-white p-3 rounded-l-lg rounded-br-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                                tempor
                                incididunt.</p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs ml-auto justify-end">
                    <div>
                        <div class="bg-blue-600 text-white p-3 rounded-l-lg rounded-br-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet.</p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs">
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                    <div>
                        <div class="bg-gray-300 p-3 rounded-r-lg rounded-bl-lg">
                            <p class="text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                                tempor
                                incididunt ut labore et dolore magna aliqua. </p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                </div>
                <div class="flex w-full mt-2 space-x-3 max-w-xs ml-auto justify-end">
                    <div>
                        <div class="bg-blue-600 text-white p-3 rounded-l-lg rounded-br-lg">
                            <p class="text-sm">Lorem ipsum dolor sit.</p>
                        </div>
                        <span class="text-xs text-gray-500 leading-none block mt-1">2 min ago</span>
                    </div>
                    <div class="flex-shrink-0 h-10 w-10 rounded-full bg-gray-300"></div>
                </div>
            </div>

            <div class="p-4">
                <textarea
                        id="chat-message-input"
                        rows="4"
                        class="resize-none block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
                        placeholder="Write message..."
                ></textarea>
            </div>
        </div>
    </div>
</div>

<%@include file="/jsp/pages/team-chat/write-expert-message-modal.jsp" %>
<%@include file="/jsp/pages/team-chat/team-settings-modal.jsp" %>

<%@include file="/jsp/shared/scripts.jsp" %>
</body>
</html>
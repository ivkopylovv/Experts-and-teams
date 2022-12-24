<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="ru.rsreu.expertsandteams.support.helper.DateHelper" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/jsp/shared/meta.jsp" %>
</head>
<body id="team-chat">
<%@include file="/jsp/shared/header.jsp" %>

<div class="container mx-auto pt-14">
    <div class="w-3/5 overflow-hidden mx-auto mt-8 relative">
        <div class="flex items-center justify-between flex-nowrap w-full p-4 space-x-2 mb-3 bg-white border border-gray-200 rounded-lg shadow-sm">
            <div class="text-xl">${chatResponse.getTeamName()}</div>
            <div class="flex items-center space-x-2">
                <button
                        type="button"
                        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-2.5 py-1.5 focus:outline-none"
                        data-modal-toggle="write-expert-message-modal">
                    Write to expert
                </button>
                <button type="button"
                        class="join-btn text-lg text-blue-600"
                        data-modal-toggle="team-settings-modal">
                    <span class="material-symbols-outlined font-bold">settings</span>
                </button>
            </div>
        </div>

        <div class="h-4/6 flex flex-col flex-grow w-full bg-white rounded-lg overflow-hidden border border-gray-200 shadow-sm">
            <div class="flex flex-col flex-grow h-0 p-4 overflow-auto">
                <c:forEach items="${chatResponse.getMessages()}" var="message">
                    <div class="flex w-full mt-2 space-x-3 max-w-xs ${message.getUserId() == user.getId() ? "ml-auto justify-end" : ""}">
                        <div>
                            <div class="bg-gray-300 p-3 rounded-r-lg rounded-bl-lg">
                                <p class="font-semibold">${message.getUserName()}</p>
                                <p class="text-sm">${message.getMessage()}</p>
                            </div>
                            <span class="text-xs text-gray-500 leading-none block mt-1">
                                    ${DateHelper.timeAsString(message.getDate())}
                            </span>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="p-4 flex items-end space-x-3">
                <textarea
                        id="chat-message-input"
                        rows="4"
                        class="resize-none block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
                        placeholder="Write message..."
                ></textarea>
                <button
                        id="send-message-btn"
                        type="button"
                        class="text-lg text-blue-600">
                    <span class="material-symbols-outlined font-bold">send</span>
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/jsp/pages/team-chat/write-expert-message-modal.jsp" %>
<%@include file="/jsp/pages/team-chat/team-settings-modal.jsp" %>

<%@include file="/jsp/shared/scripts.jsp" %>
</body>
</html>
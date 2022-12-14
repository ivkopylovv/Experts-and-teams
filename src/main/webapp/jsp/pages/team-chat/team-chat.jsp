<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="ru.rsreu.expertsandteams.model.enums.Role" %>
<html>
<head>
    <title>Team Chat</title>
    <%@include file="/jsp/shared/meta.jsp" %>
</head>
<body id="team-chat">
<%@include file="/jsp/shared/header-with-notifications.jsp" %>

<div class="container mx-auto pt-14">
    <div class="w-3/5 overflow-hidden mx-auto mt-8 relative">
        <div class="flex items-center justify-between flex-nowrap w-full p-4 space-x-2 mb-3 bg-white border border-gray-200 rounded-lg shadow-sm">
            <div class="flex items-center space-x-3">
                <span class="text-xl">${chatResponse.getTeamName()}</span>
                <span class="text-sm text-gray-500">
                    ${chatResponse.getMembersCount()} members
                </span>
            </div>
            <c:if test="${!user.getRole().equalsIgnoreCase(Role.EXPERT.getName())}">
                <div class="flex items-center space-x-2">
                    <button id="write-to-expert-btn"
                            type="button"
                            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-2.5 py-1.5 focus:outline-none"
                            data-modal-toggle="write-expert-message-modal">
                        Write to expert
                    </button>
                    <c:if test="${captain.getId() == user.getId()}">
                        <button id="settings-btn"
                                type="button"
                                class="text-lg text-blue-600"
                                data-modal-toggle="team-settings-modal">
                            <span class="material-symbols-outlined font-bold">settings</span>
                        </button>
                    </c:if>
                </div>
            </c:if>
        </div>

        <div class="h-4/6 flex flex-col flex-grow w-full bg-white rounded-lg overflow-hidden border border-gray-200 shadow-sm">
            <div id="message-container" class="flex flex-col flex-grow h-0 p-4 overflow-auto">
                <c:forEach items="${chatResponse.getMessages()}" var="message">
                    <div class="flex w-full mt-2 ${message.getUserId() != 0 ? "max-w-xs" : ""} ${message.getUserId() == user.getId() ? "ml-auto flex-col justify-end items-end" : "flex-col items-start"} ${message.getUserId() == 0 ? "items-center justify-center" : ""}">
                        <div class="${message.getUserId() == user.getId() ? "bg-blue-500 text-white" : (message.getUserId() != 0 ? "bg-gray-300" : "")} ${message.getExpertName() != null ? "bg-rose-400 text-white" : ""} ${message.getUserId() != user.getId() && message.getExpertName() == null || message.getUserId() == 0  ? "text-gray-700" : ""} p-3 rounded-lg rounded-bl-lg">
                            <c:if test="${message.getUserId() != 0}">
                                <p class="font-medium">
                                        ${message.getUserName()} ${message.getExpertName() != null
                                            ? " to ".concat(message.getExpertName().equals(user.getName()) ? "You" : message.getExpertName())
                                            : ""
                                        }
                                </p>
                            </c:if>
                            <p class="text-sm">${message.getMessage()}</p>
                        </div>
                        <c:if test="${message.getUserId() != 0}">
                            <span class="text-xs text-gray-500 leading-none block mt-1">${message.getDate()}</span>
                        </c:if>
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
<%@include file="/jsp/templates/join-request-template.jsp" %>
<%@include file="/jsp/templates/available-expert-template.jsp" %>
<%@include file="/jsp/templates/my-message-template.jsp" %>
<%@include file="/jsp/templates/other-messge-template.jsp" %>
<%@include file="/jsp/templates/my-message-to-expert-template.jsp" %>
<%@include file="/jsp/templates/other-message-to-expert-template.jsp" %>
<%@include file="/jsp/templates/common-message-template.jsp" %>
<%@include file="/jsp/templates/team-expert-template.jsp" %>

<%@include file="/jsp/shared/scripts.jsp" %>
</body>
</html>
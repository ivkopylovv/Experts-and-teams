package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.model.api.request.GetChatRequest;
import ru.rsreu.expertsandteams.model.api.request.SendMessageRequest;
import ru.rsreu.expertsandteams.model.api.response.ChatResponse;

public interface TeamMessageService {
    void sendMessage(SendMessageRequest request, Long userId);

    ChatResponse getChatHistory(GetChatRequest request, Long userId);

    ChatResponse getChatHistoryActualPart(GetChatRequest request, Long userId);
}

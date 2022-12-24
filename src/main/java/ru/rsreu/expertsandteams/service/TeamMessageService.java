package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.model.api.request.SendMessageRequest;
import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.api.response.MessageResponse;

public interface TeamMessageService {
    MessageResponse sendMessage(SendMessageRequest request, Long userId);

    ChatResponse getChatHistory(Long teamId, Long userId);

    ChatResponse getChatHistoryActualPart(Long teamId, Long userId);
}

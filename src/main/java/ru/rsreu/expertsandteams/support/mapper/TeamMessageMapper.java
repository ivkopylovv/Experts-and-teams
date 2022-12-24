package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.api.request.SendMessageRequest;
import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.api.response.MessageResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.TeamMessage;
import ru.rsreu.expertsandteams.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMessageMapper {
    private TeamMessageMapper() {
    }

    public static TeamMessage mapToTeamMessage(SendMessageRequest request, Long userId) {
        return new TeamMessage(
                new Team(
                        request.getTeamId()
                ),
                new User(
                        userId
                ),
                request.getMessage(),
                new User(
                        request.getExpertId()
                )
        );
    }

    public static ChatResponse mapToChatResponse(List<TeamMessage> teamMessages, String teamName) {
        return new ChatResponse(
                teamName,
                teamMessages
                        .stream()
                        .map(TeamMessageMapper::mapToMessageResponse)
                        .collect(Collectors.toList())
        );
    }

    public static MessageResponse mapToMessageResponse(TeamMessage teamMessage) {
        return new MessageResponse(
                teamMessage.getUser().getId(),
                teamMessage.getUser().getName(),
                teamMessage.getExpert().getName(),
                teamMessage.getMessage(),
                teamMessage.getMessageDate()
        );
    }
}

package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.model.api.request.JoinTeamDecisionRequest;
import ru.rsreu.expertsandteams.model.api.request.JoinTeamRequest;
import ru.rsreu.expertsandteams.model.api.response.JoinTeamResponse;
import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;
import ru.rsreu.expertsandteams.model.entity.User;

import java.util.List;

public interface TeamJoinRequestService {
    TeamJoinRequest findById(Long id);

    void createRequest(JoinTeamRequest request, Long userId);

    List<JoinTeamResponse> findAllCaptainRequests(Long captainId);

    void makeDecisionOnRequest(JoinTeamDecisionRequest request, User captain);
}

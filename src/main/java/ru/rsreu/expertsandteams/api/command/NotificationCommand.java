package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.constant.RequestParams;
import ru.rsreu.expertsandteams.model.api.response.NotificationResponse;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.service.NotificationService;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.RequestParams.NOTIFICATION_ID;

public class NotificationCommand extends FrontCommand {
    private NotificationService notificationService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserHelper.tryGetFromRequest(request);
        notificationService = ServiceFactory.getNotificationService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<NotificationResponse> notifications = notificationService.findAllUserNotifications(user);

        json(notifications);
    }

    @Override
    public void send() throws ServletException, IOException {
        Long notificationId = Long.parseLong(request.getParameter(NOTIFICATION_ID));

        notificationService.deleteNotification(notificationId);
    }
}
